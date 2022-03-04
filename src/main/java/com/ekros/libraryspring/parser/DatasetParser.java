package com.ekros.libraryspring.parser;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatasetParser {

  public static void main(String... args) {
    generateSqlDataset(
        "C:\\Users\\ekros\\IdeaProjects\\libraryspring\\src\\main\\resources\\csv\\BX-Books.csv");
  }

  private static final String[] GENRES = {"Fantasy", "Historical", "Comedy", "Drama",
      "Business & Finance", "Horror", "Encyclopedia", "Autobiography", "Politics"};

  @SneakyThrows
  public static void generateSqlDataset(String path) {
    List<String> exists = new ArrayList<>();
    StringBuilder sqlQuery = new StringBuilder("SET FOREIGN_KEY_CHECKS = 0;\n"
        + "TRUNCATE TABLE users;\n"
        + "TRUNCATE TABLE books;\n"
        + "TRUNCATE TABLE orders;\n"
        + "TRUNCATE TABLE hibernate_sequence;\n"
        + "SET FOREIGN_KEY_CHECKS = 1;\n\n"
        + "SET GLOBAL max_allowed_packet=134217728;\n\n"
        + "INSERT INTO books (id, name, author, year, edition, genres, count, description, description_ru) VALUES\n");

    List<String[]> rows = readCsvAsList(path);
    int id = 0;
    for (int i = 0, rowsSize = rows.size(); i < rowsSize; i++) {
      String[] row = rows.get(i);
      if (id == 1001) {
        break;
      }
      String rowStr = createRow(id, row, exists);
      if (rowStr.isEmpty()) {
        continue;
      }
      sqlQuery.append(rowStr);
      System.out.println(id++);
    }
    sqlQuery.deleteCharAt(sqlQuery.length() - 1).deleteCharAt(sqlQuery.length() - 1).append(";");
    writeToFile(sqlQuery.toString());
  }

  private static String format(String field) {
    field = field.replace("'", "").replace("\"", "").trim();
    if (!field.startsWith("'")) {
      field = "'" + field;
    }
    if (!field.endsWith("'")) {
      field = field + "'";
    }
    return field;
  }

  @SneakyThrows
  private static void writeToFile(String sqlQuery) {
    File file = new File(System.getProperty("user.dir") + "/data.sql");
    if (!file.exists()) {
      file.createNewFile();
    }
    FileWriter fw = new FileWriter(file, false);

    fw.write(sqlQuery);
    fw.flush();
    fw.close();
  }


  private static String createRow(int id, String[] row, List<String> exists) {
    try {
      int year = Integer.parseInt(row[2].replaceAll("\"", "").trim());
      String name = format(row[0]);
      String author = format(row[1]);
      String rowString = String.format("(%s,%s,%s,%s,%s,%s,10,'','')", id, name, author,
          year, format(row[3]), format(getRandomGenres()));
      if(exists.contains(name)){
        return "";
      }
      exists.add(name);
      return rowString.contains("�") ? "" : rowString + ",\n";
    }catch (NumberFormatException e){
      return "";
    }
  }

  private static String getRandomGenres() {
    int count = (int) (1 + Math.random() * 3);
    List<Integer> was = new ArrayList<>(count);
    StringBuilder genres = new StringBuilder();
    for (int i = 0; i < count; i++) {
      int index = getIndex(was);
      genres.append(GENRES[index]).append(";");
      was.add(index);
    }

    return genres.deleteCharAt(genres.length() - 1).toString();
  }

  private static int getIndex(List<Integer> was) {
    int index = (int) (Math.random() * GENRES.length);

    while (was.contains(index)) {
      index = (int) (Math.random() * GENRES.length);
    }
    return index;
  }

  private static List<String[]> readCsvAsList(String path) {

    List<String[]> rows = new ArrayList<>(200000);

    try (CSVReader reader = new CSVReader(new FileReader(path))) {

      String[] row;
      int i = 0;
      while ((row = reader.readNext()) != null) {
        String[] data = row[0].split(";");
        rows.add(new String[]{data[1], data[2], data[3], data[4]});
      }

    } catch (IOException | CsvValidationException e) {
      log.info("Error during parse csv: {}", e.getMessage(), e);
    }
    return rows;
  }
}
