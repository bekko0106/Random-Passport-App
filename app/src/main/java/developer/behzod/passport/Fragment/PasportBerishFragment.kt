package developer.behzod.passport.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import developer.behzod.passport.DataBase.AppDataBase
import developer.behzod.passport.Models.User
import developer.behzod.passport.R
import developer.behzod.passport.adapters.MyRvAdapter
import developer.behzod.passport.databinding.FragmentAddUserBinding
import developer.behzod.passport.databinding.FragmentPasportBerishBinding

class PasportBerishFragment : Fragment() {

    private lateinit var binding:FragmentPasportBerishBinding
    private lateinit var appDataBase: AppDataBase
    private lateinit var myRvAdapter: MyRvAdapter
    private lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPasportBerishBinding.inflate(layoutInflater)

        appDataBase = AppDataBase.getInstance(binding.root.context)
        list = ArrayList()
        list.addAll(appDataBase.myDao().getAllPerson())
        myRvAdapter = MyRvAdapter(list)
        binding.rv.adapter = myRvAdapter
        myRvAdapter.notifyItemInserted(list.size-1)

        binding.addUser.setOnClickListener {
            findNavController().navigate(R.id.addUserFragment)
        }

        return binding.root
    }
}