package sg.edu.rp.c346.id19004731.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView dbsTv, ocbcTv, uobTv;
    View curBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbsTv = findViewById(R.id.dbsTextView);
        ocbcTv = findViewById(R.id.ocbcTextView);
        uobTv = findViewById(R.id.uobTextView);
        registerForContextMenu(dbsTv);
        registerForContextMenu(ocbcTv);
        registerForContextMenu(uobTv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        setBank(v);
        getMenuInflater().inflate(R.menu.menu_actions, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ArrayList<String> infoArr = getInfo(curBank);

        if(item.getItemId() == R.id.website) {
            Intent intentCall = new Intent(Intent.ACTION_VIEW, Uri.parse(infoArr.get(0)));
            startActivity(intentCall);
            return true;
        } else if (item.getItemId() == R.id.contact) {
            Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + infoArr.get(1)));
            startActivity(intentCall);
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lang, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.eng) {
            setLang("eng");
            return true;
        } else if (item.getItemId() == R.id.chn) {
            setLang("chn");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //accessors
    public void setBank(View v) {
        int bankId = v.getId();

        if (bankId == dbsTv.getId())
            curBank = dbsTv;
        else if (bankId == ocbcTv.getId())
            curBank = ocbcTv;
        else if (bankId == uobTv.getId())
            curBank = uobTv;
    }

    public ArrayList<String> getInfo(View v) {
        ArrayList<String> infoArr = new ArrayList<String>();

        if (v == dbsTv) {
            infoArr.add(getString(R.string.dbs_website));
            infoArr.add(getString(R.string.dbs_contact));
        }
        else if (v == ocbcTv) {
            infoArr.add(getString(R.string.ocbc_website));
            infoArr.add(getString(R.string.ocbc_contact));
        }
        else if (v == uobTv) {
            infoArr.add(getString(R.string.uob_website));
            infoArr.add(getString(R.string.uob_contact));
        }
        return infoArr;
    }

    public void setLang(String lang) {
        if (lang.equals("eng")) {
            dbsTv.setText(getString(R.string.dbs_eng));
            ocbcTv.setText(getString(R.string.ocbc_eng));
            uobTv.setText(getString(R.string.uob_eng));
        }
        else if (lang.equals("chn")) {
            dbsTv.setText(getString(R.string.dbs_chn));
            ocbcTv.setText(getString(R.string.ocbc_chn));
            uobTv.setText(getString(R.string.uob_chn));
        }
    }

} //end of class
