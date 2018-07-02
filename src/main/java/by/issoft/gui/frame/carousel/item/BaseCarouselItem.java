package by.issoft.gui.frame.carousel.item;

import by.issoft.gui.carousel.CarouselItem;

public abstract class BaseCarouselItem implements CarouselItem {
    protected CarouselItem nextFrame;

    @Override
    public void setNextItem(CarouselItem nextItem) {
        this.nextFrame = nextItem;
    }

    @Override
    public CarouselItem nextItem() {
        return nextFrame;
    }
}
