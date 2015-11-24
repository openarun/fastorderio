package xyz.restaurationmanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.androidquery.AQuery;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Florian on 08/11/2015.
 */
public class AccountItemAdapter extends BaseAdapter {

    private Context context;
    public List<Account> acounts;

    public AccountItemAdapter(Context context, List<Account> acounts) {
        this.context = context;
        this.acounts = acounts;
    }

    @Override
    public int getCount() {
        return acounts.size();
    }

    @Override
    public Object getItem(int arg0) {
        return acounts.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        View v = convertView;
        final int pos = position;
        AccountViewHolder viewHolder = null;
        if(v==null){
            v = View.inflate(context, R.layout.content_list_accounts, null);
            viewHolder = new AccountViewHolder();
            viewHolder.nom_prenom= (TextView)v.findViewById(R.id.title_name_firstname);
           //viewHolder.date_creation= (TextView)v.findViewById(R.id.txt_date_inscription);
            viewHolder.bSuppAccount = (Button) v.findViewById(R.id.bSuppAccount);
            viewHolder.bSuppAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("Suppression de l'utilisateur");
                    String id =acounts.get(pos).getId();
                    System.out.println("id ==> "+id);
                    DeleteAccountTask task = new DeleteAccountTask();
                    task.execute(id);

                }
            });
            v.setTag(viewHolder);
        }
        else{
            viewHolder = (AccountViewHolder) v.getTag();
        }
        Account account = acounts.get(position);
        viewHolder.nom_prenom.setText(account.getPrenom());
//        viewHolder.date_creation.setText(account.getCreatedAt());
        return v;
    }

    class AccountViewHolder{
        TextView nom_prenom;
        TextView date_creation;
        Button bSuppAccount;
    }
}
