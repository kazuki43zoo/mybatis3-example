package com.example.test.typehandler;

import com.example.domain.model.Image;
import com.example.domain.model.Text;
import com.example.domain.repository.image.ImageRepository;
import com.example.domain.repository.text.TextRepository;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.UrlResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import javax.inject.Inject;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/typehandler/TypeHandlerTest.xml"})
public class TypeHandlerTest {

    @Inject
    ImageRepository imageRepository;

    @Inject
    TextRepository textRepository;

    @Transactional
    @Test
    public void blob() throws IOException {
        Image image = new Image();


        try (InputStream data = new UrlResource(new File("/Users/shimizukazuki/Downloads/README.md").toURI()).getInputStream()) {
            image.setId(UUID.randomUUID().toString());
            image.setImageData(data);
//            byte[] bytes = new byte[data.available()];
//            data.read(bytes);
//            image.setByteArrayImageData(bytes);
            image.setCreatedAt(DateTime.now());
            imageRepository.create(image);
        }

        printMethodList(ImageRepository.class);


        Image loadedImage = imageRepository.findOne(image.getId());

        System.out.println(loadedImage.getId());
        System.out.println(loadedImage.getImageData());
        System.out.println(loadedImage.getCreatedAt());

        try (FileOutputStream out = new FileOutputStream(new File("./log", "loadedImage" + System.currentTimeMillis() + ".download"))) {
            byte[] buffer = new byte[8192];
            int readedByteCount;
            while ((readedByteCount = loadedImage.getImageData().read(buffer)) > 0) {
                out.write(buffer, 0, readedByteCount);
            }
        } finally {
            loadedImage.getImageData().close();
        }


    }

    @Transactional
    @Test
    public void clob() throws IOException {
        Text text = new Text();


        try (Reader data = new InputStreamReader(new UrlResource(new File("/Users/shimizukazuki/Downloads/README.md").toURI()).getInputStream())) {
//        try (Reader data = new InputStreamReader(new UrlResource(new File("/Users/shimizukazuki/Downloads/gitbucket.war").toURI()).getInputStream())) {
            text.setId(UUID.randomUUID().toString());
            text.setTextData(data);
            text.setCreatedAt(DateTime.now());
            textRepository.create(text);
        }

        Text loadedText = textRepository.findOne(text.getId());

        System.out.println(loadedText.getId());
        System.out.println(loadedText.getTextData());
        System.out.println(loadedText.getCreatedAt());

        try (FileWriter out = new FileWriter(new File("./log", "loadedImage" + System.currentTimeMillis() + ".download"))) {
            char[] buffer = new char[8192];
            int readedByteCount;
            while ((readedByteCount = loadedText.getTextData().read(buffer)) > 0) {
                out.write(buffer, 0, readedByteCount);
            }
        } finally {
            loadedText.getTextData().close();
        }


    }


    public static void printMethodList(Class<?> clazz) {
        System.out.println();
        System.out.println(clazz.getSimpleName());
        Method[] interfaceMethods = clazz.getMethods();
        for (Method method : interfaceMethods) {
            System.out.println("  " + method);
            System.out.println("    isSynthetic = " + method.isSynthetic() + ", isBridge = " + method.isBridge());
            if (method.getAnnotations().length > 0) {
                for (Annotation annotation : method.getAnnotations()) {
                    System.out.println("    Annotation = " + annotation);
                }
            } else {
                System.out.println("    NO ANNOTATIONS!");
            }
        }
    }
}
