package ca.cmpt213.as2;

import java.io.File;
import java.io.FileFilter;

/**
 * Implements FileFilter.
 */

public class JsonFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().contains("json");
    }
}
