package telran.regex.model;

public class PhotoParse {
    public int countStrings(String str){
        String[] strs = str.split(",\\n");
        return strs.length;
    }

    public String[] getEuropPlaces(String str) {
        String[] strsAll = str.split(",\\n");
        String patternEuropPlaces = "^(Paris|London)\\\\.*$";
        return makeRegEx(strsAll, patternEuropPlaces);
    }

    public String[] getAutumnPictures(String str){
        String[] strsAll = str.split(",\\n");
        String patternAutumn = "^.+\\\\\\d{4}(09|10|11)\\d{2}_\\d{6}\\..+$";
        return makeRegEx(strsAll, patternAutumn);
    }

    public String[] getPhotosByYear(String str, int year) {
        String[] strsAll = str.split(",\\n");
        String patternPhotosByYear = "^.+\\\\" + year + "\\d{4}_\\d{6}\\..+$";
        return makeRegEx(strsAll, patternPhotosByYear);
    }

    public String[] getNightPictures(String str){
        String[] strsAll = str.split(",\\n");
        String patternAllNightPictures = "^.+_((18|19|20|21|22|23)\\d{4})\\..+$";
        return makeRegEx(strsAll, patternAllNightPictures);
    }

    public String[] getNightPictures(String str, String city){
        String[] strsAll = str.split(",\\n");
        String replStr = "[" + Character.toUpperCase(city.charAt(0)) + Character.toLowerCase(city.charAt(0)) + "]";
        city = city.replace(city.charAt(0)+"", replStr);
        String patternNightByCityPictures = "^" + city + "\\\\.*_((18|19|20|21|22|23)\\d{4})\\..+$";
        return makeRegEx(strsAll, patternNightByCityPictures);
    }

    public String[] getPhotosByType(String str, String type){
        String[] strsAll = str.split(",\\n");
        String patternType = ".*\\\\.*" + type + "$";
        return makeRegEx(strsAll, patternType);
    }

    private String[] makeRegEx(String[] strs, String pattern){
        int count = 0;
        for (int i = 0; i <= strs.length-1; i++){
            String line = strs[i].replaceAll("\"", "").trim();
            if (line.matches(pattern)) {
                count++;
            }
        }
        String[] res = new  String[count];
        count = 0;
        for (int i = 0; i <= strs.length-1; i++){
            String line = strs[i].replaceAll("\"", "").trim();
            if (line.matches(pattern)) {
                res[count] = line;
                count++;
            }
        }
        return res;
    }
}
