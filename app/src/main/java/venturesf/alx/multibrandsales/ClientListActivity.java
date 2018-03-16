package venturesf.alx.multibrandsales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import venturesf.alx.model.MBSClient;
import venturesf.alx.vo.ClientsRequest;
import venturesf.alx.multibrandsales.aws.MBSAsyncClientLambdaLs;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Clients. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ClientDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ClientListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addClientI = new Intent(view.getContext(),AddClientActivity.class);
                view.getContext().startActivity( addClientI);
            }
        });

        if (findViewById(R.id.client_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.client_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        ClientsRequest request = new ClientsRequest();

        (new MBSAsyncClientLambdaLs(this, (SimpleItemRecyclerViewAdapter)((RecyclerView) recyclerView).getAdapter()) ).execute(request);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, new ArrayList<MBSClient>(), mTwoPane));
    }



    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ClientListActivity mParentActivity;
        private List<MBSClient> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MBSClient item = (MBSClient) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString (ClientDetailFragment.ARG_ITEM_ID,  String.valueOf(item.getClientId()));
                    arguments.putBoolean(ClientDetailFragment.ARG_TWO_PANE ,mTwoPane);
                    ClientDetailFragment fragment = new ClientDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.client_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ClientDetailActivity.class);
                    intent.putExtra(ClientDetailFragment.ARG_ITEM_ID, String.valueOf(item.getClientId()));
                    intent.putExtra(ClientDetailFragment.ARG_TWO_PANE, mTwoPane);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ClientListActivity parent,
                                      List<MBSClient> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.client_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getName() + " " + mValues.get(position).getLastName() );
            holder.mContentView.setText(mValues.get(position).getPhoneNumber() );

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public void setmValues(List<MBSClient> values){
            this.mValues = values;
            super.notifyDataSetChanged();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
