# Collection Benchmark CLI

A Java CLI application for benchmarking basic operations on different collection implementations (`ArrayList`, `LinkedList`, `HashSet`, `TreeSet`) with multiple data types.

## What was improved

This project was refactored from a single-file implementation into a modular MVC-like structure:

- `model`: domain objects, enums, and benchmark report models
- `service`: business logic for data generation and benchmark execution
- `view`: CLI input/output flow
- `controller`: application orchestration
- `presenter`: output formatters (Console, CSV, JSON)

## Extra features added

- **Averaged benchmarks by iterations**: each test can run multiple times and the app reports average time.
- **JSON export support**: results can now be saved to `.json` in addition to console and CSV output.
- **Repeat run flow**: run multiple benchmark sessions without restarting the app.

## Project structure

```text
src/
  S32782Project01.java                 # Backward-compatible entry point
  benchmark/
    Main.java                          # Primary entry point
    controller/
      BenchmarkController.java
    model/
      BenchmarkConfig.java
      BenchmarkReport.java
      CollectionType.java
      DataType.java
      MyColor.java
      OutputType.java
      Person.java
      Point2D.java
      TestResult.java
      TestType.java
    presenter/
      ConsoleResultPresenter.java
      CsvResultPresenter.java
      JsonResultPresenter.java
      ResultPresenter.java
      ResultPresenterFactory.java
    service/
      CollectionBenchmarkService.java
      generator/
        DataGenerator.java
        DataGeneratorFactory.java
        DoubleGenerator.java
        IntegerGenerator.java
        LongGenerator.java
        MyColorGenerator.java
        PersonGenerator.java
        Point2DGenerator.java
    view/
      ConsoleView.java
```

## How to run

### Compile

From the project root:

```bash
javac -d out $(find src -name "*.java")
```

### Run (new entry point)

```bash
java -cp out benchmark.Main
```

### Run (legacy compatibility entry point)

```bash
java -cp out S32782Project01
```

## Benchmark operations

The app benchmarks these operations:

- `Add`
- `GetByIndex` (only applicable to list-based collections)
- `SearchElement`
- `Contains`
- `RemoveElement`

## Output options

- **Console**: prints full report in terminal
- **CSV**: writes timestamped `collection-benchmark-<timestamp>.csv`
- **JSON**: writes timestamped `collection-benchmark-<timestamp>.json`

## Notes

- `TreeSet` requires comparable data (supported by current model types).
- Benchmarks are micro-measurements based on `System.nanoTime()` and are useful mainly for relative comparison.
