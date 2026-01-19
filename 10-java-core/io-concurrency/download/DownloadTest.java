import java.util.List;

public class DownloadTest {

    public static void main(String[] args) {

        DownloadManager manager = new DownloadManager(3);

        List<String> urls = List.of(
                "http://example.com/file1.zip",
                "http://example.com/file2.zip",
                "http://example.com/file3.zip",
                "http://example.com/file4.zip",
                "http://example.com/file5.zip");

        manager.downloadAll(urls);
    }
}