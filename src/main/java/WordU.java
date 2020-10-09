import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.util.BytePictureUtils;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @outhor yumj
 * @create 2020-09-29 15:51
 */
public class WordU {

    public static void main(String[] args) throws Exception {
        Map<String, Object> datas = new HashMap<String, Object>() {
            {
                put("idPhoto", new PictureRenderData(0, 0,".jpg", BytePictureUtils.getUrlBufferedImage("https://www.scpca.com.cn/files/7c704b7e-6a3d-4852-92b2-36cc3c686911.jpg")));
                put("name","张三");
                put("certNumber","SCPCAA202000001");
                put("y1","2020");
                put("m1","9");
                put("y2","2024");
                put("m2","9");
                put("y3","2020");
                put("m3","9");
            }
        };
        XWPFTemplate template = XWPFTemplate.compile("D:/temple/template.docx")
                .render(datas);
        FileOutputStream out = new FileOutputStream("D:/temple/out_template.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
}
