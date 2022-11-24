package developer.behzod.passport.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import developer.behzod.passport.DataBase.AppDataBase
import developer.behzod.passport.Models.MyData
import developer.behzod.passport.Models.User
import developer.behzod.passport.databinding.FragmentAddUserBinding
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class AddUserFragment : Fragment() {
    private lateinit var binding: FragmentAddUserBinding
    private lateinit var appDataBase: AppDataBase
    private var path = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)


        appDataBase = AppDataBase.getInstance(binding.root.context)

        binding.imageAdd.setOnClickListener {
            getImageContent.launch("image*/")
        }
        val listSeries = ArrayList<String>()

        binding.btnSave.setOnClickListener {
            val user = User(
                path.toString().toInt(),
                binding.edtSurname.text.toString(),
                binding.edtName.text.toString(),
                binding.edtBirthday.text.toString(),
                giveSeries(listSeries)
            )
            appDataBase.myDao().addPerson(user)
            findNavController().popBackStack()
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root

    }


    private var getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri ?: return@registerForActivityResult
            binding.imageAdd.setImageURI(uri)
            val inputStream = requireActivity().contentResolver?.openInputStream(uri)
            val title = SimpleDateFormat("dd.MM.yyyy_hh.mm.ss").format(Date())
            val file = File(requireActivity().filesDir, "${title}image.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            path = file.absolutePath
            MyData.path = path
        }

    fun giveSeries(listSeries: List<String>): String {
        var seriya = ""
        val h1 = Random().nextInt(25)
        val h2 = Random().nextInt(25)
        var q = 0
        for (x in 'A'..'Z') {
            if (q == h1) {
                seriya += x
            }
            if (q == h2) {
                seriya += x
            }
            q++
        }

        for (i in 0..6) {
            seriya += Random().nextInt(10)
        }
        for (s in listSeries) {
            if (s == seriya) {
                giveSeries(listSeries)
                break
            }
        }
        return seriya
    }


}

