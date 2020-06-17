package eliezer.antonio.feedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class ItemAdapter(val list: ArrayList<Item>, val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.titulo?.text = list[position].titulo
        holder.autor?.text = list[position].autor
        //add and format date
        holder.data?.text = SimpleDateFormat("dd/mm/yyyy", Locale("pt", "AO")).format(Date(list[position].data))

      //acao do botao
        holder.btnVerMais?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }
        DownloadImageTask(holder.imagem!!).execute(list[position].imagem)

    }


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo = view.titulo_item
        val autor = view.autor_item
        val data = view.data_item
        val imagem = view.imagem_item
        val btnVerMais = view.btn_ver_mais


    }
}