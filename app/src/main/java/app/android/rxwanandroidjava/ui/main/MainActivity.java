package app.android.rxwanandroidjava.ui.main;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import app.android.rxwanandroidjava.R;
import app.android.rxwanandroidjava.common.base.BaseActivity;
import app.android.rxwanandroidjava.common.base.BaseFragment;
import app.android.rxwanandroidjava.ui.gzh.GzhFragment;
import app.android.rxwanandroidjava.ui.home.HomeFragment;
import app.android.rxwanandroidjava.ui.knowledge.KnowledgeFragment;
import app.android.rxwanandroidjava.ui.mine.MineFragment;
import app.android.rxwanandroidjava.ui.project.ProjectFragment;

public class MainActivity extends BaseActivity {

    private ArrayList<BaseFragment> mFragments;
    private int mLastFgIndex;//上一次显示的fragment下标
    private FragmentTransaction ft;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(0);
                        return true;
                    case R.id.navigation_system:
                        switchFragment(1);
                        return true;
                    case R.id.navigation_gzh:
                        switchFragment(2);
                        return true;
                    case R.id.navigation_project:
                        switchFragment(3);
                        return true;
                    case R.id.navigation_mine:
                        switchFragment(4);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });

        initFragments();
        switchFragment(0);
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化Fragments
     */
    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(KnowledgeFragment.newInstance());
        mFragments.add(GzhFragment.newInstance());
        mFragments.add(ProjectFragment.newInstance());
        mFragments.add(MineFragment.newInstance());
    }

    /**
     * 切换fragment
     *
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commitAllowingStateLoss();
            ft.add(R.id.fragment_group, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }
}
