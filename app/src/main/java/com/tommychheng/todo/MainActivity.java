package com.tommychheng.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	final int EDIT_ITEM_CODE = 21;
	List<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		TodoStore.setFilesDir(getFilesDir());

		lvItems = (ListView) findViewById(R.id.lvItems);
		items = TodoStore.load();
		itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		lvItems.setAdapter(itemsAdapter);

		items.add("first item");

		setupListViewListener();
	}

	public void launchEditItem(int index) {
		// first parameter is the context, second is the class of the activity to launch
		Intent i = new Intent(MainActivity.this, EditActivity.class);
		i.putExtra("item", items.get(index));
		startActivityForResult(i, EDIT_ITEM_CODE);
	}

	public void onAddItem(View v) {
		final EditText textEdit = (EditText) findViewById(R.id.etNewItem);

		String value = textEdit.getText().toString();

		if(!value.isEmpty()) {
			itemsAdapter.add(value);
			TodoStore.setItems(items);
			TodoStore.save();
			itemsAdapter.notifyDataSetChanged();
		}

		textEdit.setText("");
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// REQUEST_CODE is defined above
		if (resultCode == RESULT_OK && requestCode == EDIT_ITEM_CODE) {
			String item = data.getExtras().getString("item");
			int index = data.getExtras().getInt("index");

			TodoStore.setItem(index, item);
			itemsAdapter.notifyDataSetChanged();

			Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
		}
	}
	public void setupListViewListener() {
		lvItems.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				launchEditItem(position);
			}
		});

		lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				items.remove(position);
				itemsAdapter.notifyDataSetChanged();
				TodoStore.setItems(items);
				TodoStore.save();

				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
