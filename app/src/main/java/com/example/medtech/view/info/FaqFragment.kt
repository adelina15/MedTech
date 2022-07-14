package com.example.medtech.view.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.view.adapter.FaqAdapter
import com.example.medtech.data.model.Faq
import com.example.medtech.databinding.FragmentFaqBinding
import com.example.medtech.utils.Delegates

class FaqFragment : Fragment(), Delegates.FaqClicked {

    private var _binding: FragmentFaqBinding? = null
    private val binding
        get() = _binding!!

    private val faqAdapter by lazy { FaqAdapter(this) }

    private val articlesList by lazy {
        mutableListOf(
            Faq("В какую неделю беременности нужно становиться на учет и почему?", "На учет нужно встать до 12-й недели беременности, чтобы сделать все необходимые анализы, в том числе генетические, и ультрасоноскопию. На основании этой информации врач может своевременно обнаружить и лучше помочь в случае различных осложнений, а также точнее рассчитать предполагаемый срок родов"),
            Faq("Что гинеколог делает во время ежемесячного контрольного визита?", "Во время ежемесячного визита гинеколог обсуждает с беременной протекание беременности, выслушивает жалобы, разъясняет результаты анализов, отвечает на вопросы, дает рекомендации по режиму и питанию на каждом этапе беременности, исследует и оценивает общее состояние здоровья (нет ли анемии, высокого давления, отеков), взвешивает женщину и оценивает прибавку в весе, измеряет диаметр живота, который свидетельствует об адекватном росте плода, определяет позицию плода, что необходимо для планирования родов, направляет на анализ мочи, чтобы не допустить инфекций мочевых путей, которые часты у беременных, выслушивает сердцебиение плода и на основании полученной информации назначает необходимые анализы или дополнительные обследования в следующий раз."),
            Faq("Являются ли кровянистые выделения нормальным явлением при беременности? В каких случаях — да и в каких — нет? Что в таком случае делать?", "Многие женщины во время беременности жалуются на кровянистые выделения. В большинстве случаев все заканчивается хорошо, но считать выделения нормальным явлением нельзя, потому что часто они свидетельствуют о серьезных проблемах, так что нужно всегда обращаться к врачу. Без осмотра врача нельзя сказать, опасны ли кровянистые выделения или нет."),
            Faq("При каких ощущениях, жалобах и симптомах нужно обязательно обращаться к своему гинекологу или в ближайшую больницу?", "К врачу нужно обращаться в случае внезапных, острых, сильных болей в любом месте живота, которые не проходят, в случае тянущей, резкой боли внизу живота, которая усиливается, либо кровотечения. Консультация с врачом необходима и в том случае, если есть серьезные симптомы, не связанные с беременностью, например, длительная, повторная рвота, высокая температура (выше 38,5) и др."),
            Faq("О Мagne B6, фолиевой кислоте, железосодержащих препаратах и витаминах — всем ли беременным они нужны?", "Всем беременным до 12-й недели беременности нужно принимать фолиевую кислоту, потому что она отвечает за развитие нервной системы плода. Необходимость употребления остальных витаминов определяет гинеколог в зависимости от времени года, рациона и общего состояния женщины. Железосодержащие препараты нужно принимать в том случае, если анализы свидетельствуют о малокровии (в том числе скрытом) или его угрозе. Необходимость приема препаратов магния также оценивает врач. Названные препараты не должны принимать все беременные — следует проконсультироваться со своим врачом."),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_faq, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.faqRv.adapter = faqAdapter
        faqAdapter.setList(articlesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(faq: Faq) {

    }

}