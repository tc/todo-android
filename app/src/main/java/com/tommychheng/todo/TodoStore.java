package com.tommychheng.todo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * Created by tommy on 10/13/15.
 */
public class TodoStore {
	static File mFilesDir;
	static List<String> mItems = new ArrayList<String>();

	public static void setFilesDir(File f) {
		mFilesDir = f;
	}

	public static List<String> load() {
		File todoFile = new File(mFilesDir, "todo.txt");

		List<String> items;
		try {
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
		}

		mItems = items;
		return items;
	}

	public static void save() {
		File todoFile = new File(mFilesDir, "todo.txt");

		try {
			FileUtils.writeLines(todoFile, mItems);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getItems() {
		return mItems;
	}

	public static void setItems(List<String> items) {
		mItems = items;
	}

	public static void setItem(int index, String item) {
		mItems.set(index, item);
	}

}
