package project.gvr.linuxbean.globalvisionrealty.Activities;

import android.content.pm.ActivityInfo;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.ArrayList;

import project.gvr.linuxbean.globalvisionrealty.Adapters.DisplayBlogAdapter;
import project.gvr.linuxbean.globalvisionrealty.Adapters.FeaturedListingAdapter;
import project.gvr.linuxbean.globalvisionrealty.LocalModels.BlogListings;
import project.gvr.linuxbean.globalvisionrealty.LocalModels.FeaturedListing;
import project.gvr.linuxbean.globalvisionrealty.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private CardView ListingSearch,HomeWorth,Buyer,Seller,Contact,AgentLogin,About,Blog;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView ForSale,ForRent;
    WebView HgmlText;
    private MaterialSpinner ItemList;
    private RecyclerView FeaturedListRecyclerView,BlogRecyclerView;
    private ArrayList<FeaturedListing> featuredListings;
    private ArrayList<BlogListings> blogListings;

    private FeaturedListingAdapter adapter;
    private DisplayBlogAdapter BlogAdapter;
    private int columns;

    String[] all_sales = {"sale item 1","sale item 2","sale item 3","sale item 4"};
    String[] all_rent = {"rent item 1","rent item 2","rent item 3","rent item 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckIfTabletOrPhone();
        InitializeComponents();
        InitializeToolbar();
        initCollapsingToolbar();
        Clickables();
        NavigationImplementation();
        CommonMethods();
    }

    //Set Toolbar on the Activity
    public void InitializeToolbar(){
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    //find Components of XML to Activity
    public void InitializeComponents(){
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        ListingSearch = findViewById(R.id.cardView1);
        HomeWorth = findViewById(R.id.cardView2);
        Buyer = findViewById(R.id.cardView3);
        Seller = findViewById(R.id.cardView4);
        Contact = findViewById(R.id.cardView7);
        AgentLogin = findViewById(R.id.cardView8);
        About = findViewById(R.id.cardView5);
        Blog = findViewById(R.id.cardView6);
        ForSale = findViewById(R.id.for_sale);
        ForRent = findViewById(R.id.for_rent);
        ItemList = findViewById(R.id.search_by_category);
        appBarLayout = findViewById(R.id.appbar);
        HgmlText = findViewById(R.id.hgmls_text);
        BlogRecyclerView = findViewById(R.id.blog_recyclerview);
        FeaturedListRecyclerView = findViewById(R.id.featured_listing_list);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,  drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        InitializeArrayLists();
        SetLayoutForRecyclerView();
    }

    //All ArrayLists Are Initialized Here
    public void InitializeArrayLists(){
        featuredListings = new ArrayList<>();
        blogListings = new ArrayList<>();
    }

    public void SetLayoutForRecyclerView(){
        prepareFeaturedListings();
        prepareBlogListing();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,columns);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this,columns);
        FeaturedListRecyclerView.setLayoutManager(layoutManager);
        BlogRecyclerView.setLayoutManager(layoutManager1);
        adapter = new FeaturedListingAdapter(MainActivity.this,featuredListings);
        BlogAdapter = new DisplayBlogAdapter(MainActivity.this,blogListings);
        adapter.notifyDataSetChanged();
        FeaturedListRecyclerView.setAdapter(adapter);
        BlogRecyclerView.setAdapter(BlogAdapter);
        FeaturedListRecyclerView.setNestedScrollingEnabled(false);
    }

    //Prepare components for being clicked
    public void Clickables(){
        ListingSearch.setOnClickListener(this);
        HomeWorth.setOnClickListener(this);
        Buyer.setOnClickListener(this);
        Seller.setOnClickListener(this);
        Contact.setOnClickListener(this);
        AgentLogin.setOnClickListener(this);
        About.setOnClickListener(this);
        Blog.setOnClickListener(this);
        ForSale.setOnClickListener(this);
        ForRent.setOnClickListener(this);
    }

    public void CommonMethods(){
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }


    //if Hidden, show toolbar text,if expanded hide toolbar text
    private void initCollapsingToolbar() {
        collapsingToolbar.setTitle(" ");
        appBarLayout.setExpanded(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,  drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle("Home");
                    actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,  drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close);
                    drawerLayout.addDrawerListener(actionBarDrawerToggle);
                    actionBarDrawerToggle.syncState();

                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,  drawerLayout, toolbar,R.string.drawer_open, R.string.drawer_close);
                    drawerLayout.addDrawerListener(actionBarDrawerToggle);
                    actionBarDrawerToggle.syncState();
                    isShow = false;
                }
            }
        });
    }


    //OnclickListener Prpoerty implemented here
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardView1:
                Toast.makeText(this, "Clicked on Listing Search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView2:
                Toast.makeText(this, "Clicked on Home Worth", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView3:
                Toast.makeText(this, "Clicked on Buyer", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView4:
                Toast.makeText(this, "Clicked on Seller", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView7:
                Toast.makeText(this, "Clicked on Contact", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView8:
                Toast.makeText(this, "Clicked on Agent Login", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView5:
                Toast.makeText(this, "Clicked on About", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cardView6:
                Toast.makeText(this, "Clicked on Blog", Toast.LENGTH_SHORT).show();
                break;

            case R.id.for_sale:
                ItemList.setItems(all_sales);
                Toast.makeText(this, "Clicked on For Sale", Toast.LENGTH_SHORT).show();
                break;

            case R.id.for_rent:
                ItemList.setItems(all_rent);
                Toast.makeText(this, "Clicked on For Rent", Toast.LENGTH_SHORT).show();
                break;


        }
    }

    //handle Navigation items making use of id's
    public void NavigationImplementation(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home Is Clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    //A dummy list of data to be Deleted later
    private void prepareFeaturedListings() {
        FeaturedListing a = new FeaturedListing("1 Photos", "rate it", "address 1","code 1","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("2 Photos", "rate it", "address 2","code 2","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("3 Photos", "rate it", "address 3","code 3","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("4 Photos", "rate it", "address 4","code 4","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("5 Photos", "rate it", "address 5","code 5","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("6 Photos", "rate it", "address 6","code 6","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("7 Photos", "rate it", "address 7","code 7","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("8 Photos", "rate it", "address 8","code 8","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("9 Photos", "rate it", "address 9","code 9","$1600","2 Beds","2 Baths");
        featuredListings.add(a);

        a = new FeaturedListing("10 Photos", "rate it", "address 10","code 10","$1600","2 Beds","2 Baths");
        featuredListings.add(a);
    }

    private void prepareBlogListing(){
        BlogListings b = new BlogListings("Dealing with Financing");
        blogListings.add(b);

        b = new BlogListings("Preparing To Sell");
        blogListings.add(b);

        b = new BlogListings("Relocating to the Big city");
        blogListings.add(b);

        b = new BlogListings("5 Tips For Buying A Home");
        blogListings.add(b);

    }

    /*Check if the device is Phone or Tablet
    if it's a phone, set column for list as 1 and orientation to portrait
    if it's a tablet,set columns for list as 4 and orientation to landscape*/
    public void CheckIfTabletOrPhone(){
        Boolean isPhone = getResources().getBoolean(R.bool.is_phone);
        if (isPhone){
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            columns = 1;
        }else{
            setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            columns = 4;
        }
    }




}
