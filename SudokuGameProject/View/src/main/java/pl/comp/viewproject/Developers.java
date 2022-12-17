package pl.comp.viewproject;

import java.util.ListResourceBundle;

public class Developers extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"Kuba", "Jakub Stepnicki 242537"},
                {"Wojtek", "Wojciech Zyndul 242575"}
        };
    }
}
