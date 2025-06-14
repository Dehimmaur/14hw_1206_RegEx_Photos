package telran.regex.model;

public class PhotoParse {
    public int countStrings(String str){
        String[] strs = str.split(",\\n");
        return strs.length;
    }

    public String[] getEuropPlaces(String str) {
        String[] strsAll = str.split(",\\n");
        String patternEuropPlaces = "^(Paris|London|Amsterdam|Brussels|Madrid|Lisbon)\\\\.*$";
        return makeRegEx(strsAll, patternEuropPlaces);
    }

    public String[] getPicturesBySeason(String str, String season){
        String[] strsAll = str.split(",\\n");
        String patternSeason = switch (season) {
            case "Autumn" -> "^.+\\\\\\d{4}(09|10|11)\\d{2}_\\d{6}\\..+$";
            case "Winter" -> "^.+\\\\\\d{4}(01|02|12)\\d{2}_\\d{6}\\..+$";
            case "Summer" -> "^.+\\\\\\d{4}(06|07|08)\\d{2}_\\d{6}\\..+$";
            case "Spring" -> "^.+\\\\\\d{4}(03|04|05)\\d{2}_\\d{6}\\..+$";
            default -> "";
        };
        return makeRegEx(strsAll, patternSeason);
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

    public String[] getPhotosByType(String str, String type1, String type2){
        String[] strsAll = str.split(",\\n");
        String patternType = ".*\\\\.*[" + type1 + "|" + type2 + "]$";
        return makeRegEx(strsAll, patternType);
    }

    public String[] getPhotosByType(String str, String type1, String type2, String type3){
        String[] strsAll = str.split(",\\n");
        String patternType = ".*\\\\.*[" + type1 + "|" + type2 + "|" + type3 + "]$";
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
