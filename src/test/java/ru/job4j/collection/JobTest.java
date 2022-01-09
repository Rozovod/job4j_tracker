package ru.job4j.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class JobTest {

    @Test
    public void whenCompareJobAscByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Impl task", 1),
                new Job("Fix bug", 1)
        );
        Collections.sort(jobs, new JobAscByName());
        List<Job> expected = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 1)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenCompareJobDescByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Fix bug", 1),
                new Job("Impl task", 1)
        );
        Collections.sort(jobs, new JobDescByName());
        List<Job> expected = Arrays.asList(
                new Job("Impl task", 1),
                new Job("Fix bug", 1)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenCompareJobAscByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Impl task", 2),
                new Job("Impl task", 1)
        );
        Collections.sort(jobs, new JobAscByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Impl task", 1),
                new Job("Impl task", 2)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenCompareJobDescByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Impl task", 1),
                new Job("Impl task", 2)
        );
        Collections.sort(jobs, new JobDescByPriority());
        List<Job> expected = Arrays.asList(
                new Job("Impl task", 2),
                new Job("Impl task", 1)
        );
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenCompareByNameAndAcsByPriority() {
        Comparator<Job> cmpAscNameAscPriority = new JobAscByName()
                .thenComparing(new JobAscByPriority());
        int rsl = cmpAscNameAscPriority.compare(
                new Job("Fix bug", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompareByNameAndDescByPriority() {
        Comparator<Job> cmpAscNameDescPriority = new JobAscByName()
                .thenComparing(new JobAscByPriority());
        int rsl = cmpAscNameDescPriority.compare(
                new Job("Impl task", 1),
                new Job("Impl task", 0)
        );
        assertThat(rsl, greaterThan(0));
    }
}