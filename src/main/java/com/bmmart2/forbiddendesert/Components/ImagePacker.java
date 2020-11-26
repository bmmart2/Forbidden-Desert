package com.bmmart2.forbiddendesert.Components;

import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

public class ImagePacker {

    public static final Image DEFAULT_IMG = new Image(Thread.currentThread().getContextClassLoader().getResource("assets/default.bmp").toString());

    private Stack<Image> images;

    public void readDirectory(File folder) {
        images = new Stack<>();
        File[] files = folder.listFiles();
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                images.push(new Image(file.toURI().toString()));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(images);
    }

    public Image popImg() {
        if (!images.empty()) {
            return images.pop();
        }
        return null;
    }
}
