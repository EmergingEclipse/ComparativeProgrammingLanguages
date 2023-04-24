Imports System
Imports System.IO
Imports System.Text
'Caleb Millard
'SN: 613362
'CMPT 360 Spring 2023
'Assignment 2
'Title: Find And Implement an Efficient Sorting Algorithm
Module VBModule
    Public length = 0
    'the method that gathers the data from an input file
    Public Function obtainData() As Integer()
        ' inputing a data file via a path
        Dim systemPath = "C:\Users\Caleb Millard\Documents\data10000.txt"
        'this allows the file to read each line of the csv or txt file as a new number
        Dim strdata() = System.IO.File.ReadAllLines(systemPath)
        length = strdata.Length
        Dim numberdata() = New Integer(length - 1) {}

        For i As Integer = 1 To length
            numberdata(i - 1) = Convert.ToDecimal(strdata(i - 1))
        Next
        'we would be able to implement a dictionary to allow us to parse through a number And assign it a value
        'with that we would also be able to sort alphabetically because normally the > And < operands dont work on char
        Return numberdata
    End Function

    Dim bench

    'pick a number within the array to be your bench point or where you are splitting the array
    Function split(data() As Integer, bottom As Integer, top As Integer) As Integer
        Dim splitpoint As Integer

        bench = data(top)
        splitpoint = bottom - 1
        ' this is iterating throught eh available numbers and swapping them so they are in the appropriate places in the array
        For i As Integer = bottom To top - 1
            If (data(i) <= bench) Then
                splitpoint = (splitpoint + 1)
                Dim temp1 = data(splitpoint)
                data(splitpoint) = data(i)
                data(i) = temp1
            End If
        Next
        Dim temp2 = data(splitpoint + 1)
        data(splitpoint + 1) = data(top)
        data(top) = temp2
        Return splitpoint + 1
    End Function
    'the sort algorithm brings al the pieces together and uses the split method to combine them and actually sort the data
    Sub sort(data() As Integer, bottom As Integer, top As Integer)
        If (bottom < top) Then
            Dim Position As Integer = split(data, bottom, top)
            sort(data, bottom, Position - 1)
            sort(data, Position + 1, top)
        End If

    End Sub

    'this method driver combines all the aspects of the program And drives it, calling the data And the sorting algorthm methods
    Sub driver()
        Dim data() = obtainData()
        Dim length = data.Length()
        sort(data, 0, length - 1)
        Dim outputPath As String = "C:\Users\Caleb Millard\Desktop\sorted10000.txt"
        Dim datalength = data.Length
        Dim input = ""
        For i As Integer = 1 To datalength
            input = String.Join(", ", data)
        Next
        Dim streamer As FileStream = File.Create(outputPath)
        ' Add text to the file.
        Dim bytedata As Byte() = New UTF8Encoding(True).GetBytes(input)
        'creates a new file or overwrites the current output file
        streamer.Write(bytedata, 0, bytedata.Length)
        streamer.Close()
    End Sub
    Sub Main()
        driver()
    End Sub
End Module
