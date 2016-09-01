package data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageOftheDay {
    private static final String LOCATION = "http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-US";
    String dayimage = null;

    public static void main(String[] args) throws IOException {
    		String image = new ImageOftheDay().bingImage();
	}
    
    public String bingImage() throws IOException {
        URL url = new URL(LOCATION);
        URLConnection conn = url.openConnection();
        Reader reader = new InputStreamReader(conn.getInputStream());

        char[] charArray = new char[conn.getContentLength()];
        reader.read(charArray);

        String json = new String(charArray);
//        System.out.println(json);
        
        Pattern p = Pattern.compile("\"url\":\"([^\"]*)");
        Matcher m = p.matcher(json);
        boolean result = m.find();
//        System.out.println("start: "+ m.start());
//        System.out.println("end: "+ m.end());
        dayimage = (json.substring(m.start(),m.end())).substring(7);
        dayimage = "http://www.bing.com" + dayimage;
        return dayimage;

    }
}