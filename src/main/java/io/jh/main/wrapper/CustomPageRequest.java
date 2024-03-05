package io.jh.main.wrapper;


import io.jh.main.utility.ConvertUtility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomPageRequest extends PageRequest {

    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param page zero-based page index, must not be negative.
     * @param size the size of the page to be returned, must be greater than 0.
     * @param sort must not be {@literal null}, use {@link Sort#unsorted()} instead.
     */
    protected CustomPageRequest(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    public static Pageable of(int page, int size, String sortStr) {
        final int zeroIndexedPage = page - 1;
        return new CustomPageRequest(
                zeroIndexedPage, size, ConvertUtility.convertToPageRequestSort(sortStr));
    }

    public static PageRequest of(int page, int size) {
        final int zeroIndexedPage = page - 1;
        return new CustomPageRequest(
                zeroIndexedPage, size, ConvertUtility.convertToPageRequestSort(null));
    }

    public static PageRequest of(int page, int size, Sort sort) {
        final int zeroIndexedPage = page - 1;
        return new CustomPageRequest(zeroIndexedPage, size, sort);
    }
}
