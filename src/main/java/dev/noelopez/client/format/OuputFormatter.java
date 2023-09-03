package dev.noelopez.client.format;

import dev.noelopez.client.dto.CustomerResponse;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;

import java.util.List;
import java.util.stream.Collectors;

public final class OuputFormatter {

    public String coverToTable(CustomerResponse customer) {
        return coverToTable(List.of(customer));
    }

    public String coverToTable(List<CustomerResponse> customers) {
        var data = customers
                .stream()
                .map(OuputFormatter::toRow)
                .collect(Collectors.toList());
        data.add(0, addRow("id", "name", "email", "dob", "vip", "status"));

        ArrayTableModel model = new ArrayTableModel(data.toArray(Object[][]::new));
        TableBuilder table = new TableBuilder(model);
        table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
        return table.build().render(100);
    }
    private static String[] toRow(CustomerResponse c) {
        return addRow(String.valueOf(c.id()),
                c.personInfo().name(),
                c.personInfo().email(),
                c.personInfo().dateOfBirth(),
                String.valueOf(c.detailsInfo().vip()),
                c.status());
    }
    private static String[] addRow(String id, String name, String email, String dob, String vip, String status) {
        return new String[] {id, name, email, dob, vip, status};
    }
}