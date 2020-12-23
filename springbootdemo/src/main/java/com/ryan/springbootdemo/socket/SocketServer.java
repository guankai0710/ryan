package com.ryan.springbootdemo.socket;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP协议socket传输服务端
 *
 * @author: guan.kai
 * @date: 2019/11/22 15:34
 **/
@Component
public class SocketServer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

    private Thread socketThread;

    private boolean isStop = false;

    private Socket socket;

    private static final int SOCKET_PORT=10086;

    /** 报文头长度 */
    private static final int LEN_LENGHT=8;
    /** 默认编码 */
    private static final String DEFULT_ENCODING="UTF-8";

    private static final String CODE_NOT_FOUND = "{\"code\":10001,\"msg\":\"交易码不存在\",\"obj\":{}}";

    @PostConstruct
    public void startUp(){
        this.socketThread = new Thread(this);
        this.isStop = false;
        this.socketThread.setName("Socket-Thread");
        this.socketThread.start();
    }


    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(SOCKET_PORT);
            LOGGER.info("初始化Socket服务完成，端口：{}", SOCKET_PORT);

            String response = CODE_NOT_FOUND;
            while (!this.isStop){
                this.socket = server.accept();
                byte[] req = readPackage(this.socket.getInputStream());
                String reqStr = new String(new String(req, DEFULT_ENCODING).getBytes(System.getProperty("file.encoding")));
                String reqCode = JSON.parseObject(reqStr).get("reqCode").toString();
                try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream(), DEFULT_ENCODING))) {
                    if (StringUtils.isNotBlank(reqCode)){
                        for (InterfaceCode interfaceCode : InterfaceCode.values()) {
                            if (interfaceCode.getReqCode().equals(Integer.parseInt(reqCode))){
                                Class<?> aClass = Class.forName(interfaceCode.getClassName());
                                response = aClass.getMethod("execute", String.class).invoke(aClass.getDeclaredConstructor().newInstance(), reqStr).toString();
                                break;
                            }
                        }
                    }
                    writer.write(new String(wrapMessagePackage(response.getBytes()), DEFULT_ENCODING));
                    writer.flush();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * 从输出流获取报文内容，并去除报文中长度字段，编码默认utf-8
     * @param in
     * @return
     */
    private byte[] readPackage(InputStream in) throws IOException {
        //报文体长度
        byte[] bodyLen = null;
        try {
            bodyLen = getPackageLen(in, 0, LEN_LENGHT);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            throw e;
        }

        String lenStr = "-1";
        try {
            lenStr = new String(bodyLen).trim();
        } catch (NullPointerException e) {
            LOGGER.error(e.getMessage(),e);
            throw new IOException(e.getMessage());
        }

        int  len = -1;
        try {
            len = Integer.parseInt(lenStr);
        } catch (NumberFormatException e) {
            LOGGER.error(e.getMessage(),e);
            throw new IOException(e.getMessage());
        }

        //获取报文头和报文体
        byte[] body = null;
        try {
            body = getPackageLen(in, 0, len);
            body = new String(body, DEFULT_ENCODING).getBytes(System.getProperty("file.encoding"));
            LOGGER.info("=======服务端接收报文=======\n{}", new String(body));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

        return body;
    }

    /**
     * 获取报文长度
     * @param in
     * @param off
     * @param byteLen 报文体长度
     * @return
     */
    private byte[] getPackageLen(InputStream in, int off, int byteLen) throws IOException {
        byte[] buf = new byte[byteLen];
        int readLen = 0;
        while (readLen < byteLen){
            int len = in.read(buf, off + readLen, byteLen - readLen);
            if (len == -1){
                return null;
            }
            readLen += len;
        }
        return buf;
    }

    /**
     * 添加报文长度
     * @param msg
     * @return
     */
    private byte[] wrapMessagePackage(byte[] msg){
        byte[] retMsg = null;
        try {
            //将使用系统默认字符集编码的数据转换成utf-8编码
            msg = new String(msg,System.getProperty("file.encoding")).getBytes(DEFULT_ENCODING);

            retMsg = new byte[msg.length + LEN_LENGHT];
            String lenStr = String.valueOf(msg.length);
            lenStr = "0000000000" + lenStr;
            lenStr = lenStr.substring(lenStr.length() - LEN_LENGHT);
            System.arraycopy(lenStr.getBytes(), 0, retMsg, 0, LEN_LENGHT);
            System.arraycopy(msg, 0, retMsg, LEN_LENGHT, msg.length);
            LOGGER.info("=======服务端返回报文=======\n{}", new String(retMsg, DEFULT_ENCODING));
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return retMsg;
    }

    @PreDestroy
    public void shutDown() {
        this.isStop = true;
        try {
            this.socket.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
