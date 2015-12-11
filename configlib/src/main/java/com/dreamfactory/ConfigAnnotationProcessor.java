package com.dreamfactory;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import com.dreamfactory.kurtishu.api.Config;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.dreamfactory.kurtishu.api.Config"})
public class ConfigAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 获得被该注解声明的元素
        Set<? extends Element> elememts = roundEnv
                .getElementsAnnotatedWith(Config.class);
        for (Element element : elememts) {
            if (element.getKind() == ElementKind.CLASS) {
                generatedClass((TypeElement)element);
                break;
            }
        }

        return false;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Properties loadPropertiesFromFiles(String configPath) {
        System.out.println("=================== >>>>>>>>>>>>>>>>>>>> ConfigPath:" + configPath);
        String path = System.getProperty("user.dir") + "/" + configPath;
        File newFile = new File(path);
        Properties properties = new Properties();
        try {
            properties.load(new BufferedInputStream(new FileInputStream(newFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.list(System.out);
        return properties;
    }

    private void generatedClass(TypeElement element) {

        Config config = element.getAnnotation(Config.class);
        Properties properties= loadPropertiesFromFiles(config.value());

        System.out.println("=================== >>>>>>>>>>>>>>>>>>>>");
        Set<Object> keys = properties.keySet();
        Set<FieldSpec> fieldSpecs = new HashSet<FieldSpec>();

        for (Object key : keys) {
            FieldSpec fieldSpec = FieldSpec.builder(String.class, key.toString().toUpperCase())
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                    .initializer("$S", properties.get(key))
                    .build();
            fieldSpecs.add(fieldSpec);
        }

        TypeSpec configClass = TypeSpec.classBuilder("Config")
                .addModifiers(Modifier.PUBLIC)
                .addFields(fieldSpecs)
                .build();


        String packageName = getPackageName(processingEnv.getElementUtils(),
                element);

        System.out.println("=================== >>>>>>>>>>>>>>>>>>>> packname of generated class: " + packageName);
        JavaFile javaFile = JavaFile.builder(packageName, configClass)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
            System.out.println("=================== >>>>>>>>>>>>>>>>>>>>Class generated successfully!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Get the package path of the class with annotation
     * @param elementUtils
     * @param type
     * @return
     */
    private String getPackageName(Elements elementUtils, TypeElement type) {
        PackageElement pkg = elementUtils.getPackageOf(type);
        return pkg.getQualifiedName().toString();
    }
}
