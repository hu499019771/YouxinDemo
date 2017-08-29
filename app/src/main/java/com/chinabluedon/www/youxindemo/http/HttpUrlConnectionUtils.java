package com.chinabluedon.www.youxindemo.http;

import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author 胡腾
 * @time 2017/8/23  21:58
 * @desc ${TODD}
 */
public class HttpUrlConnectionUtils {

    public static final int TIME_OUT = 15 * 000;

    /**
     * 将所有的请求方法放在一个方法中,提供外界调用,符合最少知识原则面向对象原则,即调用方对被调用方的了解最少(就一个方法)
     * 这样调用者不需要花过多时间来了解被调用方细节,方便维护
     *
     * @param requset
     * @return
     * @throws IOException
     */
    public static
    @Nullable
    String excute (Requset requset) throws IOException {
        switch (requset.getMethod()) {
            case GET:
            case DELETE:
                return get(requset);
            case POST:
            case PUT:
                return post(requset);
        }
        return null;
    }

    private static String get (Requset request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();

        setConnection(request, connection);

        addHeaderParams(connection, request.getHeaders());

        return handerResponse(connection);
    }


    private static String post (Requset request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        setConnection(request, connection);
        addHeaderParams(connection, request.getHeaders());

        return handerResponse(connection);
    }

    /**
     * 添加连接和读写超时时间
     *
     * @param connection
     */
    private static void setConnection (Requset request, HttpURLConnection connection) throws IOException {
        connection.setRequestMethod(request.getMethod().name());
        connection.setConnectTimeout(TIME_OUT);
        connection.setReadTimeout(TIME_OUT);
        if(request.getMethod()== Requset.method.POST||request.getMethod()== Requset.method.PUT) {
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(request.getContent().getBytes());
        }
    }

    /**
     * 处理请求成功的结果
     *
     * @param connection
     * @return
     * @throws IOException
     */
    @Nullable
    private static String handerResponse (HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        if (status == HttpStatus.SC_OK) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.flush();
            out.close();
            return new String(out.toByteArray());
        }
        return null;
    }

    /**
     * 添加header方法
     *
     * @param connection
     * @param headers
     */
    private static void addHeaderParams (HttpURLConnection connection, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    connection.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
