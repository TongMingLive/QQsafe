package com.example.tong.qqsafe;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Tong on 2016/12/9.
 *
 * <h3>------ 该BaseFragment解决getActivity()空指针问题 ------</h3>
 * <p>
 * 可能你遇到过getActivity()返回null，或者平时运行完好的代码，在“内存重启”之后，
 * 调用getActivity()的地方却返回null，报了空指针异常。
 * <p>
 * 大多数情况下的原因：你在调用了getActivity()时，当前的Fragment已经onDetach()了宿主Activity。
 * 比如：你在pop了Fragment之后，该Fragment的异步任务仍然在执行，并且在执行完成后调用了getActivity()方法，
 * 这样就会空指针。
 * <p>
 * 解决办法：
 * 更”安全”的方法：(对于Fragment已经onDetach这种情况，我们应该避免在这之后再去调用宿主Activity对象，
 * 比如取消这些异步任务，但我们的团队可能会有粗心大意的情况，所以下面给出的这个方案会保证安全)
 * <p>
 * 在Fragment基类里设置一个Activity mActivity的全局变量，在onAttach(Activity activity)里赋值，
 * 使用mActivity代替getActivity()，保证Fragment即使在onDetach后，
 * 仍持有Activity的引用（有引起内存泄露的风险，但是相比空指针闪退，这种做法“安全”些）
 */

public class BaseFragment extends Fragment {
    protected Context mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = context;
    }
}