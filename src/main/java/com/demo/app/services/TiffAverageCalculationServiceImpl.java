package com.demo.app.services;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
public class TiffAverageCalculationServiceImpl implements TiffAverageCalculationService{
    @Override
    public void calculateAverage(String inputTiffPath, String outputTiffPath) throws IOException {
        System.out.println("Starting average calculation for image : "+inputTiffPath);
        File tiffFile = new File(inputTiffPath);
        try(ImageInputStream imageInputStream = ImageIO.createImageInputStream(tiffFile)){
            Iterator<ImageReader> readers = ImageIO.getImageReaders(imageInputStream);
            if(!readers.hasNext()) {
                throw new IllegalArgumentException("No reader for: "+ inputTiffPath);
            }

            ImageReader reader = readers.next();
            try {
                reader.setInput(imageInputStream);

                int numFrames = reader.getNumImages(true);
                System.out.println("Number of frames to process: "+numFrames);
                int width = reader.getWidth(0);
                int height = reader.getHeight(0);
                int[] average = new int[width * height];

                for(int i = 0; i <numFrames; i++) {
                    BufferedImage frame = reader.read(i);
                    int[] pixels = frame.getRGB(0,0,width,height,null,0, width);
                    for(int j=0;j<pixels.length;j++) {
                        average[j] += pixels[j];
                    }
                }

                for(int i = 0; i < average.length; i++) {
                    average[i] /= numFrames;
                }

                BufferedImage averageImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                averageImage.setRGB(0,0,width,height,average,0,width);

                ImageIO.write(averageImage, "tiff", new File(outputTiffPath));
                System.out.println("Average image file generated at: "+outputTiffPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                reader.dispose();
            }
        }
    }

}
