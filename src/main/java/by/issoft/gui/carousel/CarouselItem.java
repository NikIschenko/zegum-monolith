package by.issoft.gui.carousel;

public interface CarouselItem {
    void setNextItem(CarouselItem nextItem);
    CarouselItem nextItem();
}