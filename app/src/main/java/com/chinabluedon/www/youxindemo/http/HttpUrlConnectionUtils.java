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
    public static final String GET = "GET";
    public static final String POST = "POST";


    public static
    @Nullable
    String excute(Requset requset) throws IOException {
        switch (requset.getMethod()) {
            case GET:
                return get(requset);
            case POST:
                return post(requset);
            case PUT:

                break;
            case DELETE:

                break;
        }
        return null;
    }

    private static String get(Requset requset) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(requset.getUrl()).openConnection();
        connection.setRequestMethod(GET);
        setTimeOut(connection);

        addHeaderParams(connection, requset.getHeaders());

        return handerResponse(connection);
    }



    private static String post(Requset request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        connection.setRequestMethod(POST);
        setTimeOut(connection);
        connection.setDoOutput(true);

        addHeaderParams(connection, request.getHeaders());

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(request.getContent().getBytes());

        return handerResponse(connection);
    }

    /**
     * 添加连接和读写超时时间
     * @param connection
     */
    private static void setTimeOut(HttpURLConnection connection) {
        connection.setConnectTimeout(TIME_OUT);
        connection.setReadTimeout(TIME_OUT);
    }

    /**
     * 处理请求成功的结果
     * @param connection
     * @return
     * @throws IOException
     */
    @Nullable
    private static String handerResponse(HttpURLConnection connection) throws IOException {
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
     * 添加header参数方法
     *
     * @param connection
     * @param headers
     */
    private static void addHeaderParams(HttpURLConnection connection, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    connection.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
