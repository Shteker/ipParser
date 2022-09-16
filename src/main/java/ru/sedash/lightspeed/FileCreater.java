package ru.sedash.lightspeed;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Create file more than 1 gb
 *
 */

public class FileCreater extends Thread {
    private static final Logger LOG = Logger.getLogger(FileCreater.class.getName());
    private String fileName;

    private int needGB;

    public FileCreater(String fileName, int needGB) {
        this.fileName = fileName;
        this.needGB = needGB;
    }

    @Override
    public void run() {
        createFile(needGB);
    }


    /**
     * Method create file with ipv date as like 145.67.23.4
     */

    private void createFile(int needGB) {
        String folderName = "./src/main/resources";
        File theDir = new File(folderName);

        int need = 1024 * needGB;

        if (!theDir.exists()) {
            createDirectory(theDir);
        }
        // получаем разделитель пути в текущей операционной системе
        String fileSeparator = System.getProperty("file.separator");

        String absoluteFilePath = theDir.getAbsolutePath() + fileSeparator + fileName + ".txt";

        File file = new File(absoluteFilePath);
        try {
            if (file.createNewFile()) {
                LOG.info(absoluteFilePath + " file is creating");
                try {
                    StringBuilder sb = new StringBuilder();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));

                    while (file.length() / 1048576 < need) {
                        try {
                            //Геренерим случайное число от 1 до 999
                            Random random = new Random();
                            int num1 = random.nextInt(1000);
                            sb.append(num1).append(".");
                            int num2 = random.nextInt(1000);
                            sb.append(num2).append(".");
                            int num3 = random.nextInt(1000);
                            sb.append(num3).append(".");
                            int num4 = random.nextInt(1000);
                            sb.append(num4);
                            sb.append("\n");

                            bw.write(sb.toString());

                        }  catch (OutOfMemoryError e) {
                            LOG.info(String.valueOf(sb.length()));
                            System.gc();//а вдруг поможет
                            e.printStackTrace();
                        }
                    }
                    LOG.info(absoluteFilePath + " file created");
                    bw.close();
                    LOG.info(file.length() / 1048576 + "mb");
                } catch (IOException e) {
                    LOG.info(theDir.getAbsolutePath() + "");
                    e.printStackTrace();
                }
            } else {
                LOG.info("file" + absoluteFilePath + " already exist");

            }
        } catch (IOException e) {
            LOG.info(theDir.getAbsolutePath() + "file was not created!");
            e.printStackTrace();
        }
    }


    private String getRandomIp () {
        StringBuilder sb = new StringBuilder();
        int bound = 1000;
        Random random = new Random();

        int num1 = random.nextInt(bound);
        sb.append(num1).append(".");
        int num2 = random.nextInt(bound);
        sb.append(num2).append(".");
        int num3 = random.nextInt(bound);
        sb.append(num3).append(".");
        int num4 = random.nextInt(bound);
        sb.append(num4);
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create directory
     *
     * @param dir
     */
    private void createDirectory(File dir) {
        LOG.info("Careating directory: " + dir.getName() + "path: " + dir.getAbsolutePath());

        boolean result = false;

        try {
            dir.mkdir();
            result = true;
        } catch (SecurityException se) {
            LOG.info(se.getMessage());
        }
        if (result) {
            LOG.info("DIR created");
        }
    }

    public static void main(String[] args) throws IOException {
        FileCreater fileCreater = new FileCreater("doc"+2, 20);
        fileCreater.start();
    }
}



