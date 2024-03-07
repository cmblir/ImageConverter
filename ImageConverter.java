import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class ImageConverter {
    public static void main(String[] args) {
        // 'images' 폴더 안의 모든 파일을 처리합니다.
        File dir = new File("images/before/");
        // webp 확장자를 가진 파일만 필터링합니다.
        File[] webpFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".webp");
            }
        });

        if (webpFiles != null) {
            for (File webpFile : webpFiles) {
                try {
                    // webp 이미지를 읽어옵니다.
                    BufferedImage image = ImageIO.read(webpFile);
                    // 변환할 jpg 파일의 이름과 경로를 설정합니다. 확장자만 변경합니다.
                    File outputJpgFile = new File(webpFile.getParent(), webpFile.getName().replaceFirst("[.][^.]+$", ".jpg"));
                    // 이미지를 jpg로 저장합니다.
                    ImageIO.write(image, "jpg", outputJpgFile);

                    System.out.println("Converted: " + webpFile.getName() + " -> " + outputJpgFile.getName());
                } catch (IOException e) {
                    System.err.println("An error occurred converting " + webpFile.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}
