package WebServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MIMETypeList {
    private HashMap<String, String> typeList;

    public MIMETypeList() {
        try {
            this.typeList = loadMimeTypes();
        } catch (FileNotFoundException e) {
            System.out.println("Could not load MIMETypes.csv");
            e.printStackTrace();
        }
    }

    public String contentType(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("Ext: " + ext);
        String type = this.typeList.get(ext);
        if (type == null) {
            type = "application/octet-stream";
        }
        System.out.println("Type: " + type);

        // HashMap<String,String> types = loadMimeTypes();
        return type;
    }

    private static HashMap<String, String> loadMimeTypes() throws FileNotFoundException {
        HashMap<String, String> types = new HashMap<>(100);
        FileInputStream fin = new FileInputStream(MIMETypeList.class.getResource("MIMEtypes.csv").getFile());
        Scanner sc = new Scanner(fin).useDelimiter(",");
        while (sc.hasNextLine()) {
            types.put(sc.next(), sc.next());
            sc.nextLine();
        }
        return types;
    }
}