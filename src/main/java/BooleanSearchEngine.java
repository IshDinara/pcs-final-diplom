import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine {
    Map<String, List<PageEntry>> listWords = new HashMap<>();
    //???

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        if (pdfsDir.isDirectory()) {
            for (File item: pdfsDir.listFiles()) {
                var doc = new PdfDocument(new PdfReader(item));
                // TODO исправить счетчик
                for (int i = 1; i < 3; i++) {
                    var text = PdfTextExtractor.getTextFromPage(doc.getPage(i));
                    var words = text.split("\\P{IsAlphabetic}+");
                    Map<String, Integer> freqs = new HashMap<>();
                    for (var word : words) {
                        if (word.isEmpty()) {
                            continue;
                        }
                        word = word.toLowerCase();
                        freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                    }
                    for (Map.Entry<String, Integer> mp : freqs.entrySet()) {
                        if (!listWords.containsKey(mp.getKey())) {
                            PageEntry pageEntry = new PageEntry(item.getName(), i, mp.getValue());
                            List<PageEntry> pageEntryList = new ArrayList<>();
                            pageEntryList.add(pageEntry);
                            listWords.put(mp.getKey(), pageEntryList);
                        } else {
                            PageEntry pageEntry = new PageEntry(item.getName(), i, mp.getValue());
                            List<PageEntry> pageEntryList = listWords.get(mp.getKey());
                            pageEntryList.add(pageEntry);
                            Collections.sort(pageEntryList);
                        }
                    }
                }
            }
        }
        System.out.println(listWords);
        System.out.println();
        // прочтите тут все pdf и сохраните нужные данные,
        // тк во время поиска сервер не должен уже читать файлы
    }

    @Override
    public List<PageEntry> search(String word) {
        // тут реализуйте поиск по слову
        return Collections.emptyList();
    }
}
