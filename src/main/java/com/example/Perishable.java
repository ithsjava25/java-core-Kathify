package com.example;

import java.time.LocalDate;

public interface Perishable {
    LocalDate expirationDate();  // Rätt metodnamn enligt konvention
}
