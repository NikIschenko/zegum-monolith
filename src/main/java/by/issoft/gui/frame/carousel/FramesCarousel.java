package by.issoft.gui.frame.carousel;

import by.issoft.gui.carousel.Carousel;
import by.issoft.gui.carousel.CarouselItem;
import by.issoft.gui.frame.Frame;

import java.util.Iterator;

public class FramesCarousel {
    private final Carousel carousel;

    public FramesCarousel(Carousel carousel) {
        this.carousel = carousel;
    }

    public void createComponents() {
        while (carousel.hasNext()) {
            Frame carouselFrame = (Frame) carousel.next();
            carouselFrame.createComponents();
        }
    }

    public Frame firstItem() {
        return (Frame) carousel.firstItem();
    }
}

