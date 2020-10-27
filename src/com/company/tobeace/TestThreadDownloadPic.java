package com.company.tobeace;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThreadDownloadPic extends Thread{
    private String url;
    private String name;
    public TestThreadDownloadPic(String url,String name){
        this.name=name;
        this.url=url;
    }
    @Override
    public void run() {
        WebDownloader webDownloader=new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载文件"+name);
    }

    public static void main(String[] args) {
        TestThreadDownloadPic t1=new TestThreadDownloadPic("https://img-blog.csdn.net/20180521220215992?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTE5NzIxNzE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70","1.jpg");
        TestThreadDownloadPic t2=new TestThreadDownloadPic("https://img-blog.csdn.net/20180521220215992?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTE5NzIxNzE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70","2.jpg");
        TestThreadDownloadPic t3=new TestThreadDownloadPic("https://img-blog.csdn.net/20180521220215992?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTE5NzIxNzE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70","3.jpg");
        t1.start();
        t2.start();
        t3.start();
    }
}
class WebDownloader{
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}