package com.wx.ab.chatroom.version2;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author x43125
 */
public class NioChatClient {
    private String host;
    private int port;
    private String clientName;
    private Selector selector;
    private SocketChannel socketChannel;

    private static final String NICKNAME_START = "__NNS__";
    private static final String NICKNAME_END = "__NNE__";
    private static final String DATA_CONNECTOR = "__DC__";

    public static void main(String[] args) {
        String host;
        int port;
        if (args.length == 0) {
            host = "127.0.0.1";
            port = 6666;
        } else {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }

        NioChatClient chatClient = new NioChatClient(host, port);
        new Thread(() -> {
            while (true) {
                try {
                    chatClient.receiveMsg();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.length() == 0) {
                continue;
            }
            if (line.length() > 64) {
                System.out.println("仅允许输入64字符以内");
            }

            System.out.println("                           self: " + line);
            line = chatClient.format(line);
            chatClient.send(line);
        }
        scanner.close();
    }

    private void receiveMsg() {
        int select;
        try {
            select = selector.select();
            if (select <= 0) {
                return;
            }
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key == null) {
                    continue;
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(buffer);
                    String msg = new String(buffer.array(), StandardCharsets.UTF_8);
                    if (msg.trim().length() == 0) {
                        continue;
                    }

                    List<DataInfo> parse = parse(msg.trim());
                    printMsg(parse);
                }
            }
        } catch (IOException e) {
            reconnect();
            throw new RuntimeException("服务器宕机了...");
        }
    }

    private void reconnect() {
        System.out.println("重连...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("重连失败...");
    }

    private void printMsg(List<DataInfo> parse) {
        parse.forEach(dataInfo -> System.out.println(dataInfo.getNickName() + " > " + dataInfo.getData()));
    }



    private String format(String data) {
        return NICKNAME_START + clientName + NICKNAME_END + data + DATA_CONNECTOR;
    }

    private List<DataInfo> parse(String data) {
        String[] split = data.split(DATA_CONNECTOR);
        List<DataInfo> list = new ArrayList<>();
        for (String s : split) {
            String clientName = s.substring(s.indexOf(NICKNAME_START) + NICKNAME_START.length(), s.indexOf(NICKNAME_END));
            String msg = s.substring(s.indexOf(NICKNAME_END) + NICKNAME_END.length());
            DataInfo dataInfo = new DataInfo();
            dataInfo.setNickName(clientName);
            dataInfo.setData(msg);
            list.add(dataInfo);
        }
        return list;
    }

    private void send(String data) {
        try {
            socketChannel.write(ByteBuffer.wrap(data.trim().getBytes(StandardCharsets.UTF_8)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class DataInfo {
        private String nickName;
        private String data;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


    public NioChatClient(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            clientName = "nickname" + socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(clientName + " is ok...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
