package com.easeplantz.easeplantz.core.utils

import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.core.data.source.local.entity.MainEntity

object Data {
    fun mainMenuData(): ArrayList<MainEntity> {
        val menus = ArrayList<MainEntity>()

        menus.add(
            MainEntity(
                id = "1",
                title = "Corn Diseases",
                model = "corn",
                image = R.drawable.image_corn
            )
        )

        menus.add(
            MainEntity(
                id = "2",
                title = "Tomato Diseases",
                model = "tomato",
                image = R.drawable.image_tomato
            )
        )

        menus.add(
            MainEntity(
                id = "3",
                title = "Potato Diseases",
                model = "potato",
                image = R.drawable.image_potato
            )
        )

        return menus
    }

    fun mainResultData(): ArrayList<ResultEntity> {
        val results = ArrayList<ResultEntity>()

        results.add(
            ResultEntity(
                id = 1,
                disease = "Tomato Leaf Mold",
                description = "Ketika menanam, gunakan benih bebas penyakit atau benih yang sudah di sembuhkan. Hancurkan semua sisa tanaman setelah panen.\n" +
                        "Sanitasi greenhouse diantara musim bercocok tanam. Gunakan kipas dan hindari menyiram daun hingga terlalu basah (siram langsung ke tanah). \n" +
                        "Apabila ada tanda - tanda penyakit, gunakan fungisida sesuai dengan aturan manufaktur untuk tanda pertama infeksi penyakit.\n "
            )
        )

        results.add(ResultEntity(
            id = 2,
            disease = "Tomato Target Spot",
            description = "Hancurkan sisa tanaman lama di akhir musim, spora bisa menginfeksi tanaman baru dari sisa-sisa tanaman lama. Buang tanaman secara benar dan letakkan tumpukan kompos di tempat yang bersuhu tinggi untuk membunuh spora. \n" +
                    "Perhatikan sirkulasi udara, target spot di tomat tumbuh subur di kondisi lembab. Tanam tomat di tempat yang mendapatkan sinar matahari cukup. Pastikan tanaman memiliki jarak dan setiap tomat memiliki sirkulasi udara yang banyak.\n" +
                    "Siram tomat di pagi hari agar daun memiliki waktu untuk mengering. Siram air di bagian tanah agar daun tidak basah. Aplikasikan mulsa ke buah untuk menghindari kontak langsung dengan tanah. \n"
        ))

        results.add(ResultEntity(
            id = 3,
            disease = "Tomato Yellow Leaf Curl Virus",
            description = "Setelah terinfeksi virus ini, tidak ada cara untuk menyembuhkan. Tetapi, terdapat beberapa hal yang bisa dilakukan untuk menghindari tomat terkena penyakit ini. \n" +
                    "Gunakan varietas yang toleran\n" +
                    "Tanam di awal musim untuk menghindari populasi whitefly\n" +
                    "Gunakan jaring untuk menutupi tanaman dan membuat whitefly tidak bisa mencapai tanaman.\n" +
                    "Musnahkan rumput di sekitar tanaman\n"
        ))

        results.add(ResultEntity(
            id = 4,
            disease = "Tomato Early Blight",
            description = "Belilah benih bersertifikat\n" +
                    "Memberi jarak untuk tanaman, menjaga sirkulasi udara agar tanaman tetap kering\n" +
                    "Gunakan fungisida organik\n" +
                    "Lakukan rotasi tanaman\n"
        ))

        results.add(ResultEntity(
            id = 5,
            disease = "Tomato Mosaic Virus",
            description = "Mosaic virus sangat susah untuk dikontrol, sanitasi merupakan praktik utama untuk mengontrol mosaic virus, alat bercocok tanam harus disterilkan selama 5 menit dan dicuci dengan deterjen yang kuat. Hancurkan bibit yang tumbuh tidak normal.\n"
        ))

        results.add(ResultEntity(
            id = 6,
            disease = "Tomato Septoria Leaf Spot",
            description = "Buang daun yang terinfeksi\n" +
                    "Gunakan fungisida organik untuk prevensi awal penyebaran penyakit\n" +
                    "Gunakan fungisida kimia untuk mengontrol penyebaran penyakit yang parah. Fungisida kimia yang tidak terlalu beracun dan ampuh adalah chlorothalonil\n"
        ))

        results.add(ResultEntity(
            id = 7,
            disease = "Tomato Two Spotted Spider Mite",
            description = "Gunakan pestisida khusus untuk mites yang bernama miticide, semprotkan pada tanaman seminggu sekali, karena mites bisa menumbuhkan resistansi terhadap pestisida, maka gunakan miticide lain setiap tiga kali penggunaan.\n"
        ))

        results.add(ResultEntity(
            id = 8,
            disease = "Tomato Late Blight",
            description = "Gunakan varietas yang imun terhadap late blight\n" +
                    "Berikan tanaman jarak antara satu dengan yang lain\n" +
                    "Hindari menyiram dari atas, siramlah tanaman langsung di tanah\n" +
                    "Perhatikan cuaca\n"
        ))

        results.add(ResultEntity(
            id = 9,
            disease = "Tomato Healthy",
            description = "Selamat, tomat anda sehat"
        ))

        results.add(ResultEntity(
            id = 10,
            disease = "Tomato Bacterial Spot",
            description = "Selamat, tomat anda sehat"
        ))

        results.add(ResultEntity(
            id = 11,
            disease = "Potato Healthy",
            description = "Selamat, kentang anda sehat"
        ))

        results.add(ResultEntity(
            id = 12,
            disease = "Potato Late Blight",
            description = "Buang daun yang sudah terinfeksi\n" +
                    "Jika infeksi sudah menyebar, potong daun dan tangkai\n" +
                    "Tinggalkan tanaman untuk kurang lebih selama 2 minggu agar spores blight yang ada di permukaan mati dan kulit kentang menebal\n" +
                    "Setelah panen, cek secara rutin untuk tanada penyakit dan hilangkan yang bagian sudah terindikasi penyakit \n"
        ))

        results.add(ResultEntity(
            id = 13,
            disease = "Potato Early Blight",
            description = "Menanam varietas kentang yang tahan penyakit.\n" +
                    "Hindari penyiraman dari bagian atas tanaman dan biarkan aerasi yang cukup di antara tanaman agar dedaunan mengering secepat mungkin.\n" +
                    "rotasi tanaman selama 2 tahun. Artinya, jangan menanam kembali kentang atau tanaman lain dalam keluarga ini selama 2 tahun setelah tanaman kentang dipanen. \n" +
                    "berikan nutrisi yang cukup dan irigasi yang cukup\n" +
                    "Gali umbi hanya saat sudah benar-benar matang untuk mencegah kerusakan. Singkirkan sisa-sisa tanaman dan inang gulma di akhir musim untuk mengurangi area di mana penyakit dapat melewati musim dingin.\n"
        ))

        results.add(ResultEntity(
            id = 14,
            disease = "Corn Gray Leaf Spot",
            description = "Gunakan fungisida daun \n" +
                    "Tanam tanaman yang tahan penyakit leaf spot\n"
        ))

        results.add(ResultEntity(
            id = 15,
            disease = "Corn Common Rust",
            description = "Oleskan fungisida daun\n" +
                    "Pantau perkembangan penyakit, tahap pertumbuhan tanaman dan ramalan cuaca\n" +
                    "Perhatikan jagung untuk deteksi penyakit sejak dini\n"
        ))

        results.add(ResultEntity(
            id = 16,
            disease = "Corn Healthy",
            description = "Selamat, jagung anda sehat"
        ))

        results.add(ResultEntity(
            id = 16,
            disease = "Corn Northern Leaf Blight",
            description = "Memilih varietas hibrida yang tahan terhadap penyakit \n" +
                    "Pastikan tanaman memiliki nutrisi yang cukup. Jangan terlalu banyak memasok nitrogen, tetapi pastikan fosfor dan kalium berada pada tingkat yang optimal.\n" +
                    "Kendalikan gulma, terutama rerumputan yang mungkin menjadi inang alternatif jamur.\n" +
                    "Kumpulkan sisa-sisa tanaman dan musnahkan dengan cara dibakar atau dikubur.\n" +
                    "Berlatih rotasi tanaman; putar dengan tanaman non-rumput.\n"
        ))

    }

}
