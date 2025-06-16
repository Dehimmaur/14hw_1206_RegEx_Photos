package telran.regex.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhotoSelector {
    public static String selectPictures(String pictures, String regex){
        StringBuilder strRes = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pictures);
        while (matcher.find()) {
                strRes.append("\"").append(matcher.group()).append("\"").append(",\n");
        }
        return strRes.toString().length() > 2 ? strRes.toString().substring(0, strRes.toString().length() - 2) : "";
    }
}
