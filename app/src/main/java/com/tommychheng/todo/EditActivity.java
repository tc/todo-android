package com.tommychheng.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
	String item;
	int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		item = getIntent().getStringExtra("item");
		index = getIntent().getIntExtra("index", 0);

		final EditText textEdit = (EditText) findViewById(R.id.etItem);
		textEdit.setText(item);
	}

	public void onSave(View v) {
		final EditText textEdit = (EditText) findViewById(R.id.etItem);
		String item = textEdit.getText().toString();

		Intent data = new Intent();
		data.putExtra("item", item);
		data.putExtra("index", index);

		setResult(RESULT_OK, data);
		this.finish();
	}
}
