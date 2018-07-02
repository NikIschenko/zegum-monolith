package by.issoft.gui.carousel;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Carousel implements Iterator<CarouselItem> {
    private final CarouselItem firstItem;
    private CarouselItem currentItem;

    public Carousel(@NotNull Iterator<CarouselItem> carouselIterator) {
        firstItem = carouselIterator.next();
        relateItems(carouselIterator);
    }

    private void relateItems(Iterator<CarouselItem> carouselIterator) {
        if (firstItem != null) {
            CarouselItem previousFrame = firstItem;
            CarouselItem nextFrame = firstItem;
            while (carouselIterator.hasNext()) {
                nextFrame = carouselIterator.next();
                previousFrame.setNextItem(nextFrame);
                previousFrame = nextFrame;
            }
            nextFrame.setNextItem(firstItem);
        }
    }

    public CarouselItem firstItem() {
        return firstItem;
    }

    public Carousel(@NotNull List<CarouselItem> frames) {
        this(frames.iterator());
    }

    public Carousel(@NotNull CarouselItem ... frames) {
        this(Arrays.asList(frames));
    }

    @Override
    public boolean hasNext() {
        if (firstItem != null)
            return currentItem == null || !currentItem.nextItem().equals(firstItem);
        else
            return false;
    }

    @Override
    public CarouselItem next() {
        currentItem = currentItem == null ? firstItem : currentItem.nextItem();
        return currentItem;
    }
}