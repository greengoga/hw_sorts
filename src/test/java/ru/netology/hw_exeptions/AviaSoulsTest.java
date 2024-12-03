package ru.netology.hw_exeptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.hw_sorts.AviaSouls;
import ru.netology.hw_sorts.Ticket;
import ru.netology.hw_sorts.TicketTimeComparator;

public class AviaSoulsTest {

    @Test
    public void shouldReturnMinusOneIfPriceIsLess() {
        Ticket ticket = new Ticket("BCN", "LED", 1000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 2000, 00, 06);

        int expected = -1;
        int actual = ticket.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneIfPriceIsBigger() {
        Ticket ticket = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 00, 06);

        int expected = 1;
        int actual = ticket.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnZeroIfPricesEqual() {
        Ticket ticket = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 2000, 00, 06);

        int expected = 0;
        int actual = ticket.compareTo(ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindFirstOne() {
        AviaSouls soul = new AviaSouls();
        Ticket ticket1 = new Ticket("BCN", "LED", 1000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 2000, 00, 06);

        soul.add(ticket1);
        soul.add(ticket2);

        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = soul.search("BCN", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnSameOrderIfPriceEqual() {
        AviaSouls soul = new AviaSouls();
        Ticket ticket1 = new Ticket("BCN", "LED", 1000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 00, 06);

        soul.add(ticket1);
        soul.add(ticket2);

        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = soul.search("BCN", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSecondOneFirst() {
        AviaSouls soul = new AviaSouls();
        Ticket ticket1 = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 00, 06);

        soul.add(ticket1);
        soul.add(ticket2);

        Ticket[] expected = {ticket2, ticket1};
        Ticket[] actual = soul.search("BCN", "LED");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneIfSecondFaster() {
        TicketTimeComparator comp = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 01, 06);

        int expected = 1;
        int actual = comp.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMinusOneIfFirstFaster() {
        TicketTimeComparator comp = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("BCN", "LED", 2000, 01, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 00, 06);

        int expected = -1;
        int actual = comp.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnZeroIfSameTime() {
        TicketTimeComparator comp = new TicketTimeComparator();
        Ticket ticket1 = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 00, 06);

        int expected = 0;
        int actual = comp.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByTimeWithComparator() {
        AviaSouls soul = new AviaSouls();
        TicketTimeComparator comp = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "LED", 1000, 01, 03);
        Ticket ticket3 = new Ticket("BCN", "LED", 1500, 02, 05);

        soul.add(ticket1);
        soul.add(ticket2);
        soul.add(ticket3);

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Ticket[] actual = soul.searchAndSortBy("BCN", "LED", comp);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayIfNoTicketsFound() {
        AviaSouls soul = new AviaSouls();
        Ticket ticket1 = new Ticket("BCN", "LED", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "DME", 1000, 01, 06);

        soul.add(ticket1);
        soul.add(ticket2);

        Ticket[] expected = {};
        Ticket[] actual = soul.search("HEL", "TFS");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayIfNoTicketsFoundWithComparator() {
        AviaSouls soul = new AviaSouls();
        TicketTimeComparator comp = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("BCN", "HEL", 2000, 00, 06);
        Ticket ticket2 = new Ticket("BCN", "SVO", 1000, 01, 06);

        soul.add(ticket1);
        soul.add(ticket2);

        Ticket[] expected = {};
        Ticket[] actual = soul.searchAndSortBy("LHR", "LED", comp);
        Assertions.assertArrayEquals(expected, actual);
    }
}