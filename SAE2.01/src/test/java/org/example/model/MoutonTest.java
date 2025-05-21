package org.example.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoutonTest {

    private Plateau plateau;
    private Mouton mouton;

    @BeforeEach
    void setUp() {
        plateau = new Plateau();
        mouton = plateau.getMouton();
    }

    @AfterEach
    void tearDown() {
        mouton = null;
        plateau = null;
    }

    @Test
    void test1() {
        plateau.getCase(1,1).setType(Element.Cactus);
        mouton.mange();
        assertEquals(1,mouton.getNbCase());
    }

    @Test
    void test2() {
        plateau.getCase(1,1).setType(Element.Herbe);
        mouton.mange();
        assertEquals(2,mouton.getNbCase());
    }

    @Test
    void test3() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        mouton.mange();
        assertEquals(4,mouton.getNbCase());
    }

    @Test
    void test4() {
        plateau.getCase(1,1).setType(Element.Roche);
        assertFalse(mouton.mange());
    }

    @Test
    void test5() {
        plateau.getCase(1,1).setType(Element.Herbe);
        assertTrue(mouton.mange());
    }

    @Test
    void test6() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        assertTrue(mouton.mange());
    }

    @Test
    void test7() {
        plateau.getCase(1,1).setType(Element.Cactus);
        assertTrue(mouton.mange());
    }

    @Test
    void test8() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        mouton.mange();
        mouton.mange();
        mouton.mange();
        mouton.mange();
        mouton.mange();
        assertEquals(5,mouton.getNourriture().size());
    }

    @Test
    void test9() {
        plateau.getCase(1,1).setType(Element.Marguerite);
        mouton.mange();
        mouton.mange();
        plateau.getCase(1,1).setType(Element.Herbe);
        mouton.mange();
        assertEquals(3,mouton.getNourriture().size());
    }



}