//
// Copyright (c) ZeroC, Inc. All rights reserved.
//

package com.zeroc.library;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zeroc.demos.android.library.Demo.*;
import com.zeroc.library.controller.QueryController;
import com.zeroc.library.controller.QueryModel;

public class EditActivity extends SessionActivity
{
    private static final int SAVE_ID = Menu.FIRST;
    private static final int DISCARD_ID = Menu.FIRST + 1;
    public static final String DISCARD_TAG = "discard";

    private BookDescription _desc;

    private List<View> _authorEntries = new ArrayList<View>();
    private EditText _isbn;
    private EditText _title;
    private LinearLayout _authorsLayout;
    private LayoutInflater _inflater;
    private Button _save;

    public static class DiscardDialogFragment extends DialogFragment
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Discard changes")
                    .setMessage("Your changes will be discarded.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            ((EditActivity) getActivity()).discardOk();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                        }
                    });
            return builder.create();
        }
    }

    private void discardOk()
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void addAuthorView(String author)
    {
        final View authorView = _inflater.inflate(R.layout.author, _authorsLayout, false);
        EditText auth = (EditText)authorView.findViewById(R.id.author);
        auth.setText(author);
        _authorsLayout.addView(authorView, _authorsLayout.getChildCount()-2);
        _authorEntries.add(authorView);

        Button delete = (Button)authorView.findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                _authorsLayout.removeView(authorView);
                _authorEntries.remove(authorView);
            }
        });
    }

    private void updateBookDescription()
    {
        _isbn.setText(_desc.isbn);
        _isbn.setEnabled(_desc.proxy == null);

        _title.setText(_desc.title);

        for(String author : _desc.authors)
        {
            addAuthorView(author);
        }
        setSaveState();
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        _authorsLayout = (LinearLayout)findViewById(R.id.authorsList);

        _isbn = (EditText)findViewById(R.id.isbn);
        _isbn.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                setSaveState();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            public void onTextChanged(CharSequence s, int start, int count, int after)
            {
            }
        });

        _title = (EditText)findViewById(R.id.title);

        _inflater = getLayoutInflater();

        Button addAuthor = (Button)findViewById(R.id.addAuthor);
        addAuthor.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                addAuthorView("");
            }
        });

        _save = (Button)findViewById(R.id.saveButton);
        _save.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                save();
            }
        });
        Button discard = (Button)findViewById(R.id.discardButton);
        discard.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                DialogFragment dialog = new DiscardDialogFragment();
                dialog.show(getSupportFragmentManager(), DISCARD_TAG);
            }
        });
    }

    protected void setSaveState()
    {
        String isbn = _isbn.getText().toString().trim();
        if(_desc.proxy == null && isbn.length() == 0)
        {
            _save.setEnabled(false);
        }
        else
        {
            _save.setEnabled(true);
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        _queryController.setListener(new QueryController.Listener()
        {
            public void onDataChange(QueryModel data, boolean saved)
            {
                if(saved)
                {
                    finish();
                }
                else
                {
                    _desc = data.currentBook;
                    updateBookDescription();
                }
            }

            public void onError()
            {
                showDialogError();
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem saveItem = menu.findItem(SAVE_ID);
        saveItem.setEnabled(_save.isEnabled());
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, SAVE_ID, 0, R.string.menu_save);
        menu.add(0, DISCARD_ID, 0, R.string.menu_discard);
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
        case SAVE_ID:
            save();
            return true;

        case DISCARD_ID:
            DialogFragment dialog = new DiscardDialogFragment();
            dialog.show(getSupportFragmentManager(), DISCARD_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Hook the back key to save the item, if necessary.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(_save.isEnabled())
            {
                save();
            }
            else
            {
                finish();
            }
            return true;
        }
        return false;
    }

    private void save()
    {
        List<String> authors = new ArrayList<String>();

        for(View authorView : _authorEntries)
        {
            EditText auth = (EditText)authorView.findViewById(R.id.author);
            String a = auth.getText().toString().trim();
            if(a.length() > 0)
            {
                authors.add(a);
            }
        }
        _desc.authors = authors;
        _desc.title = _title.getText().toString().trim();
        _desc.isbn = _isbn.getText().toString().trim();

        if(!_queryController.saveBook(_desc))
        {
            finish();
        }
    }
}
