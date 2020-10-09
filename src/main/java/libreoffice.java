import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * libreoffice转化
 *
 * @outhor yumj
 * @create 2020-10-09 16:16
 */
public class libreoffice {

    private static final Logger logger = LoggerFactory.getLogger(libreoffice.class);

    public static void main(String[] args) throws NullPointerException {
        long start = System.currentTimeMillis();
        String srcPath = "D:/temple/out_template.docx", desPath = "D:/temple";
        String command = "";
        String osName = System.getProperty("os.name");
        if (osName.contains("Windows")) {
            command = "soffice --headless --convert-to png " + srcPath + " --outdir " + desPath;
            exec(command);
        }
        new File(srcPath).delete();
        long end = System.currentTimeMillis();
        logger.info("用时:{} ms", end - start);
    }

    public static boolean exec(String command) {
        Process process;// Process可以控制该子进程的执行或获取该子进程的信息
        try {
            logger.info("exec cmd : {}", command);
            process = Runtime.getRuntime().exec(command);// exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
            // 下面两个可以获取输入输出流
            InputStream errorStream = process.getErrorStream();
            InputStream inputStream = process.getInputStream();
        } catch (IOException e) {
            logger.error(" exec {} error", command, e);
            return false;
        }

        int exitStatus = 0;
        try {
            exitStatus = process.waitFor();// 等待子进程完成再往下执行，返回值是子线程执行完毕的返回值,返回0表示正常结束
            // 第二种接受返回值的方法
            int i = process.exitValue(); // 接收执行完毕的返回值
            logger.info("i----" + i);
        } catch (InterruptedException e) {
            logger.error("InterruptedException  exec {}", command, e);
            return false;
        }

        if (exitStatus != 0) {
            logger.error("exec cmd exitStatus {}", exitStatus);
        } else {
            logger.info("exec cmd exitStatus {}", exitStatus);
        }

        process.destroy(); // 销毁子进程
        process = null;
        return true;
    }
}
