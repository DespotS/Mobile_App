package com.example.cafeteria;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuAdapter;

import com.example.cafeteria.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView menuListView;
    private ArrayList<Menu> menuList;
    private MenuAdapter menuAdapter;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuListView = findViewById(R.id.menuListView);
        menuList = generateSampleMenu();

        menuAdapter = new MenuAdapter(this, R.layout.menu_item, menuList);
        menuListView.setAdapter(menuAdapter);

        menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Handle item click (e.g., show prices, allow marking as favorite)
            }
        });
    }

    private ArrayList<Menu> generateSampleMenu() {
        ArrayList<Menu> sampleMenu = new ArrayList<>();

        sampleMenu.add(new Menu("Monday", "Spaghetti Bolognese", 8.99));
        sampleMenu.add(new Menu("Monday", "Caesar Salad", 6.99));

        sampleMenu.add(new Menu("Tuesday", "Chicken Curry", 9.99));
        sampleMenu.add(new Menu("Tuesday", "Vegetable Stir-Fry", 7.99));

        sampleMenu.add(new Menu("Wednesday", "Grilled Salmon", 10.99));
        sampleMenu.add(new Menu("Wednesday", "Caprese Pizza", 8.49));

        sampleMenu.add(new Menu("Thursday", "Beef Tacos", 7.49));
        sampleMenu.add(new Menu("Thursday", "Quinoa Bowl", 6.99));

        sampleMenu.add(new Menu("Friday", "BBQ Chicken Wrap", 8.99) {
            @Override
            public MenuItem add(CharSequence title) {
                return null;
            }

            @Override
            public MenuItem add(int titleRes) {
                return null;
            }

            @Override
            public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
                return null;
            }

            @Override
            public MenuItem add(int groupId, int itemId, int order, int titleRes) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(CharSequence title) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(int titleRes) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
                return null;
            }

            @Override
            public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
                return 0;
            }

            @Override
            public void removeItem(int id) {

            }

            @Override
            public void removeGroup(int groupId) {

            }

            @Override
            public void clear() {

            }

            @Override
            public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {

            }

            @Override
            public void setGroupVisible(int group, boolean visible) {

            }

            @Override
            public void setGroupEnabled(int group, boolean enabled) {

            }

            @Override
            public boolean hasVisibleItems() {
                return false;
            }

            @Override
            public MenuItem findItem(int id) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public MenuItem getItem(int index) {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
                return false;
            }

            @Override
            public boolean isShortcutKey(int keyCode, KeyEvent event) {
                return false;
            }

            @Override
            public boolean performIdentifierAction(int id, int flags) {
                return false;
            }

            @Override
            public void setQwertyMode(boolean isQwerty) {

            }
        });
        sampleMenu.add(new Menu("Friday", "Shrimp Pasta", 10.49) {
            @Override
            public MenuItem add(CharSequence title) {
                return null;
            }

            @Override
            public MenuItem add(int titleRes) {
                return null;
            }

            @Override
            public MenuItem add(int groupId, int itemId, int order, CharSequence title) {
                return null;
            }

            @Override
            public MenuItem add(int groupId, int itemId, int order, int titleRes) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(CharSequence title) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(int titleRes) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title) {
                return null;
            }

            @Override
            public SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes) {
                return null;
            }

            @Override
            public int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems) {
                return 0;
            }

            @Override
            public void removeItem(int id) {

            }

            @Override
            public void removeGroup(int groupId) {

            }

            @Override
            public void clear() {

            }

            @Override
            public void setGroupCheckable(int group, boolean checkable, boolean exclusive) {

            }

            @Override
            public void setGroupVisible(int group, boolean visible) {

            }

            @Override
            public void setGroupEnabled(int group, boolean enabled) {

            }

            @Override
            public boolean hasVisibleItems() {
                return false;
            }

            @Override
            public MenuItem findItem(int id) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public MenuItem getItem(int index) {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean performShortcut(int keyCode, KeyEvent event, int flags) {
                return false;
            }

            @Override
            public boolean isShortcutKey(int keyCode, KeyEvent event) {
                return false;
            }

            @Override
            public boolean performIdentifierAction(int id, int flags) {
                return false;
            }

            @Override
            public void setQwertyMode(boolean isQwerty) {

            }
        });

        return sampleMenu;
    }
}