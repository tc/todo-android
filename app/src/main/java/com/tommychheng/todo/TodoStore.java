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
	static File filesDir;

	public static void setFilesDir(File f) {
		filesDir = f;
	}

	public static List<String> readItems() {
		File todoFile = new File(filesDir, "todo.txt");

		List<String> items;
		try {
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
		}

		return items;
	}

	public static void writeItems(List<String> items) {
		File todoFile = new File(filesDir, "todo.txt");

		try {
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
