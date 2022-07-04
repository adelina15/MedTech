package com.example.medtech.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.adapter.ArticlesAdapter
import com.example.medtech.data.Article
import com.example.medtech.databinding.FragmentArticlesBinding
import com.example.medtech.utils.Delegates

class ArticlesFragment : Fragment(), Delegates.ArticleClicked {

    private var _binding: FragmentArticlesBinding? = null
    private val binding
        get() = _binding!!

    private val articlesAdapter by lazy { ArticlesAdapter(this) }

    private val articlesList by lazy {
        mutableListOf(
            Article("Дневник беременности", R.drawable.first,"Ведите дневник беременности", "Это только кажется, что данная затея — странность. На самом деле, перечитывать все этапы изменения вашего психологического и физического состояния, вы сделаете много полезных выводов для себя. Вести его удобнее по неделям: с 1-й по 40-ю"),
            Article("Плановое  УЗИ", R.drawable.second, "Не бойтесь делать УЗИ", "В эти 9 месяцев УЗИ станет главным исследованием, которое позволит понимать, как протекает беременность и развивается ваш малыш. Будущие мамы часто беспокоятся о том, когда стоит делать первое ультразвуковое исследование и можно ли проводить его на маленьких сроках. Раньше существовало мнение, что УЗИ — процедура опасная, и делать ее часто не рекомендуется. Сегодня специалисты уверяют, что с медицинской точки зрения подобная процедура абсолютно безопасна для вас и будущего ребенка. Современные ультразвуковые приборы обладают функциями трехмерной и четырехмерной визуализации, которые позволяют не только наблюдать за движениями ребенка, но и на ранних стадиях выявлять редкие или комплексные нарушения его сердечной деятельности. "),
            Article("Профилактика растяжек", R.drawable.first, "Практикуем прфилактику растяжек", "Большинство женщин всерьез опасается, что эластичность кожи после родов не вернется. Одна из главных эстетических проблем, которая волнует женщин во время беременности — растяжки. И во избежании этой проблемы советуем не пренебрегать профилактикой от растяжек. "),
            Article("Токсикоз", R.drawable.second, "Как проявляется токсикоз", "Токсикоз – это своеобразная реакция организма, на возникшие вследствие беременности изменения. Плод воспринимается как потенциальная угроза здоровью, но период адаптации не велик и ограничивается, как правило, промежутком от шести до двенадцати недель. Уже на четвертом месяце, неприятные симптомы токсикоза исчезают практически у всех женщин."),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.articlesRv.adapter = articlesAdapter
        articlesAdapter.setList(articlesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(article: Article) {
        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailsFragment(article)
        findNavController().navigate(action)
    }
}