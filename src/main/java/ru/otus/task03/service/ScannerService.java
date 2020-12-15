package ru.otus.task03.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ScannerService {
    private final Scanner scanner;

    public ScannerService(Scanner scanner) {
        this.scanner = scanner;
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public String next() {
        return scanner.next();
    }
}
