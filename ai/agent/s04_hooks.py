#!/usr/bin/env python3
"""
s04: Hooks — move extension logic out of the loop, onto hooks.
  User types query
       │
       ▼
  ┌──────────────────┐
  │ UserPromptSubmit │ ── trigger_hooks() before LLM
  └────────┬─────────┘
           ▼
  ┌────────────┐     ┌─────────────────────────────┐
  │  messages  │────▶│  LLM (stop_reason=tool_use?)│
  └────────────┘     │   No ──▶ Stop hooks ──▶ exit │
                     │   Yes ──▶ tool_use block ──┐ │
                     └────────────────────────────┘ │
                                                    ▼
                                          ┌──────────────────┐
                                          │ trigger_hooks()   │
                                          │  PreToolUse:      │
                                          │   permission_hook │
                                          │   log_hook        │
                                          └───────┬──────────┘
                                                  │ (not blocked)
                                          ┌───────▼──────────┐
                                          │ TOOL_HANDLERS[x]  │
                                          └───────┬──────────┘
                                                  │
                                          ┌───────▼──────────┐
                                          │ trigger_hooks()   │
                                          │  PostToolUse:     │
                                          │   large_output    │
                                          └───────┬──────────┘
                                                  │
                                          results ──▶ back to messages
Changes from s03:
  + HOOKS registry (event -> list of callbacks)
  + register_hook() / trigger_hooks()
  + context_inject_hook (UserPromptSubmit)
  + permission_hook, log_hook (PreToolUse)
  + large_output_hook (PostToolUse)
  + summary_hook (Stop)
  - check_permission() removed from loop body
    (logic moved into permission_hook, triggered via PreToolUse)
Run: python s04_hooks/code.py
Needs: pip install anthropic python-dotenv + ANTHROPIC_API_KEY in .env
"""



# 避免console输入有问题

import os
from pathlib import Path

from anthropic import Anthropic
from dotenv import load_dotenv


try:
    import readline
    readline.parse_and_bind('set bind-tty-special-chars off')
    readline.parse_and_bind('set input-meta on')
    readline.parse_and_bind('set output-meta on')
    readline.parse_and_bind('set convert-meta off')
except ImportError:
    pass

# 读取环境变量
load_dotenv(override=True)

# 设置工作空间
WORKDIR = Path.cwd()

# 创建 client
client = Anthropic(
    base_url=os.getenv("ZHIPUAI_URL"),
    api_key=os.getenv("ZHIPUAI_API_KEY"),
)
# 选择 model
MODEL = os.getenv("MODEL_ID")
SYSTEM = f"You are a coding agent at {WORKDIR}. Use tools solve tasks. Act, don't explain"



# 主干
if __name__ == "__main__":
    print("s04: Hooks - extension logic on hooks, loop stays clean")
    print("Type a question, press Enter. Type q to quit.\n")

    # 本次 Agent loop 的记忆
    history = []
    while True:
        try:
            query = input("\033[36ms04 >> \033[0m")
        except (EOFError, KeyboardInterrupt):
            break
        # 如果输入符合条件则退出
        if query.strip().lower() in ("q", "exit", ""):
            break
        trigger_hooks("UserPromptSubmit", query)
        # 初始化用户输入的记忆
        history.append({"role": "user", "content": query})
        # loop 主体
        agent_loop(history)
        for block in history[-1]["content"]:
            if getattr(block, "type", None) == "text":
                print(block.text)
                
        print()
        